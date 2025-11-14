package com.corespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    @Autowired
    @Qualifier("upiPayment")
    private PaymentProcess paymentProcess;

    public void makePayment(double amount) {
        paymentProcess.makePayment(amount);
    }
}
