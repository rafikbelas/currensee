package com.rafikbelas.currensee.resource;

import com.rafikbelas.currensee.dto.VatResultDTO;
import com.rafikbelas.currensee.service.VatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vat")
public class VATController {

    @Autowired
    VatService vatService;

    @GetMapping("/verify")
    VatResultDTO verify(@RequestParam("VAT") String vat,
                  @RequestParam("api_key") String apiKey) {

        String countryCode = vatService.getCountryCode(vat, apiKey);
        return new VatResultDTO(countryCode);
    }

}
