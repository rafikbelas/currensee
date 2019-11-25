package com.rafikbelas.currensee.service;

import com.rafikbelas.currensee.service.api.CloudMersiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VatServiceImpl implements VatService {

    @Autowired
    CloudMersiveService cloudMersiveService;

    @Override
    public String getCountryCode(String vat, String apiKey) {
        return cloudMersiveService.getCountryCode(vat, apiKey);
    }
}
