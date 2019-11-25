package com.rafikbelas.currensee.service;

public interface CurrencyService {
    double getRate(String from, String to, String apiKey);
}
