package com.rafikbelas.currensee.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.AbstractMap;

@Getter
@Setter
@Data
public class CurrencyRateDTO {

    private boolean success;
    private String source;
    private Timestamp timestamp;
    private AbstractMap<String, Double> quotes;
}