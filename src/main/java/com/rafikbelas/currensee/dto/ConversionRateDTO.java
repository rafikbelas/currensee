package com.rafikbelas.currensee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ConversionResult", description = "The result of the conversion from USD to target currency")
public class ConversionRateDTO {

    @ApiModelProperty(notes = "${ConversionRateDTO.from}")
    private String from;
    @ApiModelProperty(notes = "${ConversionRateDTO.to}")
    private String to;
    @ApiModelProperty(notes = "${ConversionRateDTO.amount}")
    @JsonProperty("amount")
    private double convertedAmount;

    public ConversionRateDTO(String from, String to, double amount, double rate) {
        this.from = from;
        this.to = to;
        this.convertedAmount = amount * rate;
    }
}
