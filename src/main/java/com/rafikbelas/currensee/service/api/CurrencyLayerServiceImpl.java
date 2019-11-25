package com.rafikbelas.currensee.service.api;

import com.rafikbelas.currensee.dto.CurrencyRate;
import com.rafikbelas.currensee.exception.CurrencyLayerApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyLayerServiceImpl implements CurrencyLayerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${currencylayer.api.endpoint}")
    private String endpoint;

    @Override
    public double getRate(String from, String to, String apiKey) throws CurrencyLayerApiException {

        String url = endpoint + "?access_key=" + apiKey + "&currencies=" + to + "&source=" + from + "&format=1";
        CurrencyRate response = restTemplate.getForObject(url, CurrencyRate.class);
        if (response.isSuccess()) {
            return response.getQuotes().get(from + to);
        } else {
            throw new CurrencyLayerApiException("CurrencyLayer API error.");
        }

    }
}

