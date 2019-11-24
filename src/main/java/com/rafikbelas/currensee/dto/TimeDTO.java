package com.rafikbelas.currensee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class TimeDTO {

    private LocalTime now;
}
