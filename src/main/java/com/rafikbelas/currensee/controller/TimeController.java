package com.rafikbelas.currensee.controller;

import com.rafikbelas.currensee.dto.TimeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@Api(tags = "Time", description = "REST API for Time")
public class TimeController {

    @ApiOperation(value = "${TimeController.now.description}", response = TimeDTO.class)
    @GetMapping("/now")
    TimeDTO now() {
        return new TimeDTO(LocalTime.now());
    }
}
