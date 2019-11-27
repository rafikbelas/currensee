package com.rafikbelas.currensee.controller;

import com.rafikbelas.currensee.dto.ConversionResultDTO;
import com.rafikbelas.currensee.service.CurrencyService;
import com.rafikbelas.currensee.validator.CurrencyCodeConstraint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
@Validated
@Api(tags = "Currency", description = "REST API for Currency")
public class CurrencyController {
    public static final String FROM = "USD";

    @Autowired
    private CurrencyService currencyService;

    @ApiOperation(value = "${CurrencyController.convert.description}", response = ConversionResultDTO.class)
    @GetMapping("/convert")
    ConversionResultDTO convert(@ApiParam(value = "${CurrencyController.convert.to.description}", required = true, example = "EUR")
                                @RequestParam @CurrencyCodeConstraint String to,
                                @ApiParam(value = "${CurrencyController.convert.amount.description}", required = true, example = "80.00")
                                @RequestParam double amount,
                                @ApiParam(value = "${CurrencyController.convert.apiKey.description}", required = true)
                                @RequestParam("api_key") String apiKey) {

        double rate = currencyService.getRate(FROM, to, apiKey);
        return new ConversionResultDTO(FROM, to, amount, rate);

    }
}
