package com.rafikbelas.currensee.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.AbstractMap;

@Getter
@Setter
public class CurrencyRate {

    private boolean success;
    private AbstractMap<String, Double> quotes;
}