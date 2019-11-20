package com.rafikbelas.currensee.resource;

import com.rafikbelas.currensee.dto.TimeDTO;
import com.rafikbelas.currensee.mapper.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class TimeController {

    @Autowired
    private TimeMapper timeMapper;

    @GetMapping("/now")
    TimeDTO now() {
        return timeMapper.toTimeDTO(LocalTime.now());
    }
}
