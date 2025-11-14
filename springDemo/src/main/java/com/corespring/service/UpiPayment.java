package com.corespring.service;

import org.springframework.stereotype.Component;

@Component
public class UpiPayment implements PaymentProcess {

    public UpiPayment() {
        System.out.println("UPI Constructor Run.");
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Paid " + amount + " by UPI.");
    }
}
