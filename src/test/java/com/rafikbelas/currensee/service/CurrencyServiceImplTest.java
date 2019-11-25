package com.rafikbelas.currensee.service;

import com.rafikbelas.currensee.service.api.CurrencyLayerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CurrencyService.class})
class CurrencyServiceImplTest {

    @MockBean
    CurrencyLayerService currencyLayerService;

    @Test
    void getRate() {
        final Double rate = new Random().nextDouble();
        Mockito.when(currencyLayerService.getRate(anyString(), anyString(), anyString()))
                .thenReturn(rate);

        final Double result = currencyLayerService.getRate(anyString(), anyString(), anyString());

        verify(currencyLayerService, times(1)).getRate(anyString(), anyString(), anyString());
        assertThat(result).isEqualTo(rate);

    }
}