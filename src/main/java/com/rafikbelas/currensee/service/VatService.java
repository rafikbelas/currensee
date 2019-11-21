package com.rafikbelas.currensee.service;

import com.cloudmersive.client.invoker.ApiException;

public interface VatService {

    String getCountryCode(String vat, String apiKey) throws ApiException;
}
