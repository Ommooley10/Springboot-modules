package com.codingshuttle.om.module1.hw;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ChocolateFlavour implements Frosting, Syrup{
    public void getFrostingType(){
        System.out.println("Chocolate frosting");
    }

    public void getSyrupType(){
        System.out.println("Chocolate syrup");
    }
}
