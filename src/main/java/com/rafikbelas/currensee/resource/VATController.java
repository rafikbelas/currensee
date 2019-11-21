package com.rafikbelas.currensee.resource;

import com.cloudmersive.client.VatApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.VatLookupRequest;
import com.cloudmersive.client.model.VatLookupResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vat")
public class VATController {

    @GetMapping("/verify")
    VatLookupResponse verify(@RequestParam("VAT") String vat,
                             @RequestParam("api_key") String apiKey) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey(apiKey);

        VatApi apiInstance = new VatApi();
        VatLookupRequest input = new VatLookupRequest().vatCode(vat);

        try {
            VatLookupResponse result = apiInstance.vatVatLookup(input);
            return result;
        } catch (ApiException e) {
            System.err.println("Exception when calling VatApi#vatVatLookup");
            e.printStackTrace();
        }
        return null;
    }

}
