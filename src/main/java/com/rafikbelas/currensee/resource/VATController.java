package com.rafikbelas.currensee.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vat")
public class VATController {

    @GetMapping("/verify")
    String verify(@RequestParam("VAT") String vat) {
        return "Verifying VAT " + vat;
    }

}
