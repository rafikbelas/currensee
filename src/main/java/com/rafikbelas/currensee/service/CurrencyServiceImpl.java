package com.rafikbelas.currensee.service;

import com.rafikbelas.currensee.service.api.CurrencyLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyLayerService currencyLayerService;

    @Override
    public double getaRate(String from, String to, String apiKey) {
        return currencyLayerService.getRate(from, to, apiKey);
    }

}
