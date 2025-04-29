package com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс ListPerfomanceTest тестирует метод measurePerformance из класса ListPerfomance.
 * Метод measurePerformance измеряет время выполнения различных операций над списками.
 */
public class ListPerfomanceTest {

    @Test
    void testAddPerformanceWithArrayList() {
        List<Integer> list = new ArrayList<>();
        int iterations = 100_000;
        String operation = "add";

        long timeTaken = ListPerfomance.measurePerformance(list, iterations, operation);

        assertTrue(timeTaken >= 0, "Время выполнения операции добавления должно быть неотрицательным");
    }

    @Test
    void testAddPerformanceWithLinkedList() {
        List<Integer> list = new LinkedList<>();
        int iterations = 100_000;
        String operation = "add";

        long timeTaken = ListPerfomance.measurePerformance(list, iterations, operation);

        assertTrue(timeTaken >= 0, "Время выполнения операции добавления должно быть неотрицательным");
    }

    @Test
    void testGetPerformanceWithArrayList() {
        List<Integer> list = new ArrayList<>();
        int iterations = 100_000;
        String operation = "get";

        long timeTaken = ListPerfomance.measurePerformance(list, iterations, operation);

        assertTrue(timeTaken >= 0, "Время выполнения операции получения элементов должно быть неотрицательным");
    }

    @Test
    void testGetPerformanceWithLinkedList() {
        List<Integer> list = new LinkedList<>();
        int iterations = 100_000;
        String operation = "get";

        long timeTaken = ListPerfomance.measurePerformance(list, iterations, operation);

        assertTrue(timeTaken >= 0, "Время выполнения операции получения элементов должно быть неотрицательным");
    }

    @Test
    void testRemovePerformanceWithArrayList() {
        List<Integer> list = new ArrayList<>();
        int iterations = 100_000;
        String operation = "remove";

        long timeTaken = ListPerfomance.measurePerformance(list, iterations, operation);

        assertTrue(timeTaken >= 0, "Время выполнения операции удаления элементов должно быть неотрицательным");
    }

    @Test
    void testRemovePerformanceWithLinkedList() {
        List<Integer> list = new LinkedList<>();
        int iterations = 100_000;
        String operation = "remove";

        long timeTaken = ListPerfomance.measurePerformance(list, iterations, operation);

        assertTrue(timeTaken >= 0, "Время выполнения операции удаления элементов должно быть неотрицательным");
    }
}