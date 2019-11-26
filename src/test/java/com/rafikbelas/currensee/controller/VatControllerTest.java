package com.rafikbelas.currensee.controller;

import com.rafikbelas.currensee.exception.InvalidVatException;
import com.rafikbelas.currensee.service.VatService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(VatController.class)
class VatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VatService vatService;

    String urlTemplate = "/vat/verify";
    String apiKey = "API_KEY";
    String vat = "CZ25123891";
    String countryCode = "CZ";

    @Test
    void verify() throws Exception {
        doReturn(countryCode).when(vatService).getCountryCode(vat, apiKey);

        mockMvc.perform(get(urlTemplate)
                .param("vat", vat)
                .param("api_key", apiKey))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("countryCode").value(countryCode));
    }

    @Test
    void verify_shouldReturnBadRequestIfParamIsMissing() throws Exception {
        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isBadRequest());
    }

    @Test
    void convert_returnsBadRequestIfVatIsInvalid() throws Exception {
        vat = "AA123456";
        doThrow(InvalidVatException.class).when(vatService).getCountryCode(vat, apiKey);

        mockMvc.perform(get(urlTemplate)
                .param("vat", vat)
                .param("api_key", apiKey))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}