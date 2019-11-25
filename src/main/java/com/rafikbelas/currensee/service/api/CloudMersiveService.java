package com.rafikbelas.currensee.service.api;

import com.cloudmersive.client.model.VatLookupResponse;

public interface CloudMersiveService {
    String getCountryCode(String vat, String apiKey);

    VatLookupResponse getVatLookupResponse(String vat, String apiKey);
}
