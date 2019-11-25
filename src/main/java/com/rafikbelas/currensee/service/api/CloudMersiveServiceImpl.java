package com.rafikbelas.currensee.service.api;

import com.cloudmersive.client.VatApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.VatLookupRequest;
import com.cloudmersive.client.model.VatLookupResponse;
import com.rafikbelas.currensee.exception.CloudMersiveApiException;
import com.rafikbelas.currensee.exception.InvalidVatException;
import org.springframework.stereotype.Service;

@Service
public class CloudMersiveServiceImpl implements CloudMersiveService {

    @Override
    public String getCountryCode(String vat, String apiKey) {
        VatLookupResponse result = getVatLookupResponse(vat, apiKey);

        if (result.isIsValid())
            return result.getCountryCode();
        else
            throw new InvalidVatException("Invalid VAT");
    }

    @Override
    public VatLookupResponse getVatLookupResponse(String vat, String apiKey) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey(apiKey);

        VatApi apiInstance = new VatApi();
        VatLookupRequest input = new VatLookupRequest().vatCode(vat);

        try {
            return apiInstance.vatVatLookup(input);
        } catch (ApiException e) {
            throw new CloudMersiveApiException("CloudMersive API error");
        }
    }
}
