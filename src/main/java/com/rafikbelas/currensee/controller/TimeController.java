package com.rafikbelas.currensee.controller;

import com.rafikbelas.currensee.dto.TimeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class TimeController {

    @GetMapping("/now")
    TimeDTO now() {
        return new TimeDTO(LocalTime.now());
    }
}
