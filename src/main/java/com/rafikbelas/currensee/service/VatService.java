package com.rafikbelas.currensee.service;

import com.cloudmersive.client.invoker.ApiException;
import com.rafikbelas.currensee.exception.InvalidVatException;

public interface VatService {
    String getCountryCode(String vat, String apiKey) throws ApiException, InvalidVatException;
}
