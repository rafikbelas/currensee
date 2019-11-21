package com.rafikbelas.currensee.service;

import com.rafikbelas.currensee.dto.CurrencyRate;
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
    public Double getaRate(String from, String to, String apiKey) {

        String url = endpoint + "?access_key=" + apiKey + "&currencies=" + to + "&source=" + from + "&format=1";

        CurrencyRate response = restTemplate.getForObject(url, CurrencyRate.class);

        String key = from + to;
        return response.getQuotes().get(key);
    }
}
