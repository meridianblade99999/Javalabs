package com.impl;

import com.interfaces.SomeInterface;

/**
 * Вторая реализация SomeInterface
 */
public class OtherImpl implements SomeInterface {

    @Override
    public void doSomething() {
        System.out.println("B");
    }

}
