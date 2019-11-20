package com.rafikbelas.currensee.resource;

import com.rafikbelas.currensee.dto.CurrencyRateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currency")
@PropertySource("currencylayer.properties")
public class CurrencyController {

    @Value("${currencylayer.api.endpoint}")
    private String endpoint;

    private static final String FROM = "USD";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/convert")
    CurrencyRateDTO convert(@RequestParam String to, @RequestParam("api_key") String apiKey) {

        String url = endpoint
                + "?access_key=" + apiKey
                + "&currencies=" + to
                + "&source=" + FROM
                + "&format=1";

        CurrencyRateDTO response = restTemplate.getForObject(
                url,
                CurrencyRateDTO.class
        );

        return response;
    }
}
