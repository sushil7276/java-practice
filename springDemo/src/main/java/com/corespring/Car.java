package com.corespring;

import org.springframework.stereotype.Component;

@Component
public class Car {

    Car() {
        System.out.println("Car Default Constructor");
    }

    public void drive() {
        System.out.println("Car is running");
    }
}
