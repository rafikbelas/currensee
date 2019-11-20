package com.rafikbelas.currensee.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversionRateDTO {

    private String from;
    private String to;
    @JsonProperty("amount")
    private double convertedAmount;

    public ConversionRateDTO(String from, String to, double amount, double rate) {
        this.from = from;
        this.to = to;
        this.convertedAmount = amount * rate;
    }
}
