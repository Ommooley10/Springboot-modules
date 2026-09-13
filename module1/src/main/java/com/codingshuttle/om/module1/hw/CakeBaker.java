package com.codingshuttle.om.module1.hw;

import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    Frosting frostingObj;
    Syrup syrupObj;

    public CakeBaker(Frosting frostingObj, Syrup syrupObj){
        this.frostingObj = frostingObj;
        this.syrupObj = syrupObj;
    }

    public void bakeCake(){
        frostingObj.getFrostingType();
        syrupObj.getSyrupType();
        System.out.println("Cake is baked");
    }
}
