package com.rafikbelas.currensee.service;

import com.rafikbelas.currensee.exception.CloudMersiveApiException;
import com.rafikbelas.currensee.exception.CurrencyLayerApiException;
import com.rafikbelas.currensee.exception.ExternalApiException;
import com.rafikbelas.currensee.service.api.CloudMersiveService;
import com.rafikbelas.currensee.service.api.CloudMersiveServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {VatServiceImpl.class, CloudMersiveServiceImpl.class})
class VatServiceImplTest {

    @Autowired
    VatService vatService;

    @MockBean
    CloudMersiveService cloudMersiveService;

    @Test
    void getCountryCode() {
        final String countryCode = "CZ";
        when(cloudMersiveService.getCountryCode(anyString(), anyString()))
                .thenReturn(countryCode);

        final String result = vatService.getCountryCode(anyString(), anyString());

        verify(cloudMersiveService, times(1)).getCountryCode(anyString(), anyString());
        assertThat(result).isEqualTo(countryCode);
    }

    @Test
    void getRate_whenServiceThrowsExternalApiException() {
        doThrow(CloudMersiveApiException.class)
                .when(cloudMersiveService).getCountryCode(anyString(), anyString());

        assertThrows(ExternalApiException.class, () -> vatService.getCountryCode(anyString(), anyString()));
        verify(cloudMersiveService, times(1)).getCountryCode(anyString(), anyString());

    }
}