package com.example.demo.controllers.components;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculatorComponent {

    public static BigDecimal Add(BigDecimal first, BigDecimal second){
        return first.add(second).setScale(2,BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal subtract(BigDecimal first, BigDecimal second){
        return first.subtract(second).setScale(2,BigDecimal.ROUND_CEILING);
    }
}
