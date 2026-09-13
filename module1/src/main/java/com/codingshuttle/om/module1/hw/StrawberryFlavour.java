package com.codingshuttle.om.module1.hw;

import org.springframework.stereotype.Component;

@Component
public class StrawberryFlavour implements Frosting, Syrup{
    public void getFrostingType(){
        System.out.println("Strawberry frosting");
    }

    public void getSyrupType(){
        System.out.println("Strawberry syrup");
    }
}
