package com.rafikbelas.currensee.service.api;

import com.cloudmersive.client.model.VatLookupResponse;
import com.rafikbelas.currensee.exception.CloudMersiveApiException;
import com.rafikbelas.currensee.exception.ExternalApiException;
import com.rafikbelas.currensee.exception.InvalidVatException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CloudMersiveServiceImplTest {

    @SpyBean
    CloudMersiveService cloudMersiveService;

    @Test
    void getCountryCode_whenEnteredVatIsValid() {
        String vat = "CZ123123";
        String countryCode = "CZ";

        doReturn(new VatLookupResponse().isValid(true).countryCode(countryCode).vatNumber(vat))
                .when(cloudMersiveService).getVatLookupResponse(nullable(String.class), nullable(String.class));

        String result = cloudMersiveService.getCountryCode(eq(vat), anyString());

        assertThat(result).isEqualTo(countryCode);
        verify(cloudMersiveService, times(1))
                .getVatLookupResponse(nullable(String.class), nullable(String.class));
    }

    @Test
    void getCountryCode_whenEnteredVatIsNotValid() {

        String vat = "SS123123";
        String countryCode = "SS";

        doReturn(new VatLookupResponse().isValid(false).countryCode(countryCode).vatNumber(vat))
                .when(cloudMersiveService).getVatLookupResponse(nullable(String.class), nullable(String.class));

        assertThrows(InvalidVatException.class, () -> cloudMersiveService.getCountryCode(eq(vat), anyString()));
        verify(cloudMersiveService, times(1))
                .getVatLookupResponse(nullable(String.class), nullable(String.class));

    }

    @Test
    void getCountryCode_whenApiCallFails() {
        doThrow(CloudMersiveApiException.class)
                .when(cloudMersiveService).getVatLookupResponse(nullable(String.class), nullable(String.class));

        assertThrows(ExternalApiException.class, () -> cloudMersiveService.getCountryCode(anyString(), anyString()));
    }

}