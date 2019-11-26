package com.rafikbelas.currensee.service.api;

import com.rafikbelas.currensee.dto.CurrencyRate;
import com.rafikbelas.currensee.exception.CloudMersiveApiException;
import com.rafikbelas.currensee.exception.ExternalApiException;
import com.rafikbelas.currensee.service.CurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CurrencyLayerServiceImpl.class})
class CurrencyLayerServiceImplTest {

    @SpyBean
    CurrencyLayerService currencyLayerService;

    @MockBean
    RestTemplate restTemplate;

    String from = "USD";
    String to = "EUR";
    String apiKey = "API_KEY";
    Double rate;

    @BeforeEach
    void init() {
        rate = new Random().nextDouble() * 100;
    }

    @Test
    void getRate() {

        AbstractMap quotes = new HashMap();
        quotes.put(from+to, rate);
        CurrencyRate response = new CurrencyRate(true, quotes);

        doReturn(response).when(currencyLayerService).getCurrencyRate(from, to, apiKey);

        double result = currencyLayerService.getRate(from, to, apiKey);

        assertThat(result).isEqualTo(rate);

    }

    @Test
    void getRate_shouldReturnBadRequestIfApiCallFails() {

        doThrow(CloudMersiveApiException.class)
                .when(currencyLayerService).getCurrencyRate(from, to, apiKey);

        assertThrows(ExternalApiException.class, () -> currencyLayerService.getRate(from, to, apiKey));
    }
}