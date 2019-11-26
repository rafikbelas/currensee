package com.rafikbelas.currensee.controller;

import com.rafikbelas.currensee.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    String urlTemplate = "/currency/convert";
    String apiKey = "API_KEY";
    String to = "EUR";
    Double rate;
    Double amount;

    @BeforeEach()
    void init() {
        Random random = new Random();
        rate = random.nextDouble() * 100;
        amount = random.nextDouble() * 100;
    }

    @Test
    void convert() throws Exception {

        doReturn(rate).when(currencyService).getRate(anyString(), anyString(), anyString());

        mockMvc.perform(get(urlTemplate)
                .param("to", to)
                .param("amount", String.valueOf(amount))
                .param("api_key", apiKey))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value(rate * amount));
    }

    @Test
    void convert_returnsBadRequestIfCurrencyConstraintIsViolated() throws Exception {
        to = "AAA";

        mockMvc.perform(get(urlTemplate)
                .param("to", to)
                .param("amount", String.valueOf(amount))
                .param("api_key", apiKey))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void convert_returnsBadRequestIfArgumentIsMissing() throws Exception {

        mockMvc.perform(get(urlTemplate))
                .andExpect(status().isBadRequest());
    }
}