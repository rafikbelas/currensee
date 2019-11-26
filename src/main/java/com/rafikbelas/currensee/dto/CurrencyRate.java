package com.rafikbelas.currensee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.AbstractMap;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyRate {

    private boolean success;
    private AbstractMap<String, Double> quotes;
}