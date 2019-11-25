package com.rafikbelas.currensee.resource;

import com.rafikbelas.currensee.dto.ConversionRateDTO;
import com.rafikbelas.currensee.service.CurrencyService;
import com.rafikbelas.currensee.validator.CurrencyCodeConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
@PropertySource("currencylayer.properties")
@Validated
public class CurrencyController {
    public static final String FROM = "USD";

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/convert")
    ConversionRateDTO convert(@RequestParam("to") @CurrencyCodeConstraint String to,
                              @RequestParam("amount") double amount,
                              @RequestParam("api_key") String apiKey) {

        double rate = currencyService.getaRate(FROM, to, apiKey);
        return new ConversionRateDTO(FROM, to, amount, rate);

    }
}
