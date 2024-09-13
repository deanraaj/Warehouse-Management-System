package com.example.warehouse.customException;

public class PriceShouldGreaterThanZero extends RuntimeException{
    public PriceShouldGreaterThanZero(String message) {
        super(message);
    }
}
