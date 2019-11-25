package com.rafikbelas.currensee.service.api;

public interface CurrencyLayerService {
    double getRate(String from, String to, String apiKey);
}
