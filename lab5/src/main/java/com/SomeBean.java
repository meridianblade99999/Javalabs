package com;

import com.annotations.AutoInjectable;
import com.interfaces.SomeInterface;
import com.interfaces.SomeOtherInterface;

/**
 * Класс {@code SomeBean} представляет собой объект, в который можно автоматически
 * внедрять зависимости с использованием аннотации {@code AutoInjectable}.
 *
 * Поля класса помечены аннотацией {@code AutoInjectable}, что указывает на необходимость
 * автоматической инициализации реализации соответствующих интерфейсов через механизм
 * внедрения зависимостей.
 *
 * Метод {@code foo()} используется для вызова методов внедренных объектов и демонстрирует
 * функциональность внедрения зависимостей в данном классе.
 */
public class SomeBean {

    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Метод вызывает методы, реализованные во внедренных объектах.
     *
     * Метод предназначен для демонстрации функциональности автоматического
     * внедрения зависимостей через аннотацию {@code AutoInjectable}. Вызывает
     * {@code doSomething()} у объекта типа {@code SomeInterface} и
     * {@code doSomeOther()} у объекта типа {@code SomeOtherInterface}.
     *
     * Используемые зависимости автоматически инициализируются на основе конфигурации,
     * предоставляемой механизмом внедрения зависимостей.
     */
    public void foo(){
        field1.doSomething();
        field2.doSomeOther();
    }

}