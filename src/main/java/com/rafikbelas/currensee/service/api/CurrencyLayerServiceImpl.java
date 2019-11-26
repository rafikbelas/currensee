package com.rafikbelas.currensee.service.api;

import com.rafikbelas.currensee.dto.CurrencyRate;
import com.rafikbelas.currensee.exception.CurrencyLayerApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:currencylayer.properties")
public class CurrencyLayerServiceImpl implements CurrencyLayerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${currencylayer.api.endpoint}")
    private String endpoint;

    @Override
    public double getRate(String from, String to, String apiKey) throws CurrencyLayerApiException {
        CurrencyRate response = getCurrencyRate(from, to, apiKey);

        if (response.isSuccess()) {
            return response.getQuotes().get(from + to);
        } else {
            throw new CurrencyLayerApiException("CurrencyLayer API error.");
        }
    }

    @Override
    public CurrencyRate getCurrencyRate(String from, String to, String apiKey) {
        String url = endpoint + "?access_key=" + apiKey + "&currencies=" + to + "&source=" + from + "&format=1";
        return restTemplate.getForObject(url, CurrencyRate.class);
    }
}

