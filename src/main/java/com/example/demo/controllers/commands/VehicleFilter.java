package com.example.demo.controllers.commands;


import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
public class VehicleFilter {

    private String phrase;

    @PositiveOrZero
    private Float minPrice;

    @PositiveOrZero
    private Float maxPrice;


    public boolean isEmpty(){
        return StringUtils.isEmpty(phrase) && minPrice == null && minPrice == null;
    }

    public void clear(){
        this.phrase = "";
        this.minPrice = null;
        this.maxPrice = null;
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }


}