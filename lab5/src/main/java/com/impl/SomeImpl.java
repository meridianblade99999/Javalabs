package com.impl;

import com.interfaces.SomeInterface;

/**
 * Первая реализация SomeInterface
 */
public class SomeImpl implements SomeInterface {

    @Override
    public void doSomething() {
        System.out.println("A");
    }

}