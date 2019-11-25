package com.rafikbelas.currensee.service;

import com.rafikbelas.currensee.service.api.CurrencyLayerService;
import com.rafikbelas.currensee.service.api.CurrencyLayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CurrencyServiceImpl.class, CurrencyLayerServiceImpl.class})
class CurrencyServiceImplTest {

    @Autowired
    CurrencyService currencyService;

    @MockBean
    CurrencyLayerService currencyLayerService;

    @Test
    void getRate() {
        final Double rate = new Random().nextDouble();
        when(currencyLayerService.getRate(anyString(), anyString(), anyString()))
                .thenReturn(rate);

        final Double result = currencyService.getRate(anyString(), anyString(), anyString());

        verify(currencyLayerService, times(1)).getRate(anyString(), anyString(), anyString());
        assertThat(result).isEqualTo(rate);

    }
}