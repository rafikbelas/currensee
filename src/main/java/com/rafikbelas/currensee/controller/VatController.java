package com.rafikbelas.currensee.controller;

import com.rafikbelas.currensee.dto.VatResultDTO;
import com.rafikbelas.currensee.service.VatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vat")
@Api(tags = "VAT", description = "REST API for VAT")
public class VatController {

    @Autowired
    VatService vatService;

    @GetMapping("/verify")
    @ApiOperation(value = "${VatController.verify.description}", response = VatResultDTO.class)
    VatResultDTO verify(@ApiParam(value = "${VatController.verify.vat.description}", required = true, example = "CZ25123891")
                        @RequestParam("vat") String vat,
                        @ApiParam(value = "${VatController.verify.apiKey.description}", required = true)
                        @RequestParam("api_key") String apiKey) {

        String countryCode = vatService.getCountryCode(vat, apiKey);
        return new VatResultDTO(countryCode);
    }

}
