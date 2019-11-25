package com.rafikbelas.currensee.controller;

import com.rafikbelas.currensee.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
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

    @Test
    void convert() throws Exception {
        Random random = new Random();
        Double rate = random.nextDouble() * 100;
        Double amount = random.nextDouble();
        String to = "DZD";
        String apiKey = "API_KEY";

        doReturn(rate).when(currencyService).getRate(anyString(), anyString(), anyString());

        mockMvc.perform(MockMvcRequestBuilders.get("/currency/convert")
                .param("to", to)
                .param("amount", String.valueOf(amount))
                .param("api_key", apiKey))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value(rate * amount));
    }
}