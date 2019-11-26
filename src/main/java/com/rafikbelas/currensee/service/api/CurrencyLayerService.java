package com.rafikbelas.currensee.service.api;

import com.rafikbelas.currensee.dto.CurrencyRate;

public interface CurrencyLayerService {
    double getRate(String from, String to, String apiKey);

    CurrencyRate getCurrencyRate(String from, String to, String apiKey);
}
