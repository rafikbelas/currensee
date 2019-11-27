package com.rafikbelas.currensee.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@ApiModel(value = "Time", description = "The current time")
public class TimeDTO {
    @ApiModelProperty(notes = "${TimeDTO.now}")
    private LocalTime now;
}
