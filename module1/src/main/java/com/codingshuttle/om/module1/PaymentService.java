package com.codingshuttle.om.module1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
@Component
public class PaymentService {
    public void pay(){
        System.out.println("Paying...");
    }

    @PostConstruct
    public void afterInterval(){
        System.out.println("Before paying...");
    }

    @PreDestroy
    public void beforeDestroyed(){
        System.out.println("After payment...");
    }
}
