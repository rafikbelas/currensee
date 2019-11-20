package com.rafikbelas.currensee.resource;

import com.rafikbelas.currensee.dto.TimeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class TimeController {

    @GetMapping("/now")
    TimeDTO now() {
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setNow(LocalTime.now());
        return timeDTO;
    }
}
