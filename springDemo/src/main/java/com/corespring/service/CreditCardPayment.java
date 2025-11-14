package com.corespring.service;

import org.springframework.stereotype.Component;

@Component
public class CreditCardPayment implements PaymentProcess {

    public CreditCardPayment() {
        System.out.println("Credit Card Constructor Run.");
    }

    @Override
    public void makePayment(double amount) {
        System.out.println("Paid " + amount + " by credit card.");
    }
}
