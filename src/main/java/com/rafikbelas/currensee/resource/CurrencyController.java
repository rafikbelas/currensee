package com.rafikbelas.currensee.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @GetMapping("/convert")
    String convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return "Converting " + amount + " from USD to " + to;
    }
}
