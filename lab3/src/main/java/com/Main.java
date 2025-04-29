package com;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс Main демонстрирует сравнение производительности операций над реализациями интерфейса List:
 * ArrayList и LinkedList. В программе измеряется время выполнения операций "add", "get"
 * и "remove" на основе фиксированного количества итераций. Результаты выводятся в табличном виде.
 */
public class Main {

    /**
     * Точка входа в программу. Метод сравнивает производительность операций "add", "get"
     * и "remove" для двух реализаций интерфейса List: ArrayList и LinkedList.
     * Каждая операция выполняется заданное количество раз, после чего измеряется затраченное время.
     * Результаты производительности выводятся в табличном формате.
     *
     * @param args список аргументов командной строки, не используется в данном методе
     */
    public static void main(String[] args) {
        final int ITERATIONS = 100_000;

        long arrayListAddTime = ListPerfomance.measurePerformance(new ArrayList<>(), ITERATIONS, "add");
        long linkedListAddTime = ListPerfomance.measurePerformance(new LinkedList<>(), ITERATIONS, "add");

        long arrayListGetTime = ListPerfomance.measurePerformance(new ArrayList<>(), ITERATIONS, "get");
        long linkedListGetTime = ListPerfomance.measurePerformance(new LinkedList<>(), ITERATIONS, "get");

        long arrayListRemoveTime = ListPerfomance.measurePerformance(new ArrayList<>(), ITERATIONS, "remove");
        long linkedListRemoveTime = ListPerfomance.measurePerformance(new LinkedList<>(), ITERATIONS, "remove");

        System.out.printf("%-12s | %-10s | %-10s%n", "Method", "ArrayList", "LinkedList");
        System.out.println("-------------------------------");
        System.out.printf("%-12s | %-10d | %-10d%n", "Add", arrayListAddTime, linkedListAddTime);
        System.out.printf("%-12s | %-10d | %-10d%n", "Get", arrayListGetTime, linkedListGetTime);
        System.out.printf("%-12s | %-10d | %-10d%n", "Remove", arrayListRemoveTime, linkedListRemoveTime);
    }

}