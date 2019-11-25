package com.rafikbelas.currensee.service;

import com.rafikbelas.currensee.service.api.CloudMersiveService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {VatService.class})
class VatServiceImplTest {

    @MockBean
    CloudMersiveService cloudMersiveService;

    @Test
    void getCountryCode() {
        final String countryCode = "CZ";
        Mockito.when(cloudMersiveService.getCountryCode(anyString(), anyString()))
                .thenReturn(countryCode);

        final String result = cloudMersiveService.getCountryCode(anyString(), anyString());

        verify(cloudMersiveService, times(1)).getCountryCode(anyString(), anyString());
        assertThat(result).isEqualTo(countryCode);
    }
}