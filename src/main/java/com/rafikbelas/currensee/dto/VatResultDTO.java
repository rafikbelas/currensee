package com.rafikbelas.currensee.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "VatResult", description = "Result of verification.")
public class VatResultDTO {
    @ApiModelProperty(notes = "${VatResultDTO.countryCode}")
    private String countryCode;
}
