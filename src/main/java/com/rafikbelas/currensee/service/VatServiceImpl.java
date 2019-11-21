package com.rafikbelas.currensee.service;

import com.cloudmersive.client.VatApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.VatLookupRequest;
import com.cloudmersive.client.model.VatLookupResponse;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VatServiceImpl implements VatService {
    @Override
    public String getCountryCode(String vat, String apiKey) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey(apiKey);

        VatApi apiInstance = new VatApi();
        VatLookupRequest input = new VatLookupRequest().vatCode(vat);

        try {
            VatLookupResponse result = apiInstance.vatVatLookup(input);
            if (result.isIsValid())
                return result.getCountryCode();
            else
                throw new NoSuchElementException("VAT Not Found");
        } catch (ApiException e) {
            System.err.println("Exception when calling VatApi#vatVatLookup");
            e.printStackTrace();
        }

        return null;
    }
}
