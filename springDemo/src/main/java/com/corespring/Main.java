package com.corespring;

import com.corespring.service.PaymentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        Car car = context.getBean(Car.class);
//        car.drive();

        // Payment Service Test
        PaymentService paymentService = context.getBean(PaymentService.class);
        paymentService.makePayment(500);

    }
}
