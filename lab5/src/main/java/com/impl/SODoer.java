package com.impl;

import com.interfaces.SomeOtherInterface;

/**
 * Реализация SomeOtherInterface
 */
public class SODoer implements SomeOtherInterface {

    @Override
    public void doSomeOther(){
        System.out.println("C");
    }

}
