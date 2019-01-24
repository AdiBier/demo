package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle")
public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(){
        super(String.format("Pojazd nie istnieje"));
    }

    public VehicleNotFoundException(Long id){
        super(String.format("Pojazd o id #d nie istnieje", id));
    }
}
