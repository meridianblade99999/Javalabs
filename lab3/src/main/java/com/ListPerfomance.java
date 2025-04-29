package com;

import java.util.List;

/**
 * Класс предоставляет утилиты для измерения производительности операций над реализациями интерфейса List.
 * Позволяет измерять время выполнения операций добавления, получения и удаления элементов из списка.
 */
public class ListPerfomance {

    /**
     * Измеряет время выполнения указанной операции над списком за заданное количество итераций.
     *
     * @param list реализация интерфейса List, над которой будут выполняться операции
     * @param iterations количество итераций, для которых будет производиться выполнение операции
     * @param operation операция, производительность которой нужно измерить.
     *                  Возможные значения:
     *                  "add" - добавление элементов в список,
     *                  "get" - получение элементов из списка,
     *                  "remove" - удаление элементов из списка
     * @return время выполнения операции в миллисекундах
     */
    public static long measurePerformance(List<Integer> list, int iterations, String operation) {
        long startTime = System.currentTimeMillis();

        switch (operation) {
            case "add" -> {
                for (int i = 0; i < iterations; i++) {
                    list.add(i);
                }
            }
            case "get" -> {
                for (int i = 0; i < iterations; i++) {
                    list.add(i);
                }
                for (int i = 0; i < iterations; i++) {
                    list.get(i);
                }
            }
            case "remove" -> {
                for (int i = 0; i < iterations; i++) {
                    list.add(i);
                }
                for (int i = iterations - 1; i >= 0; i--) {
                    list.remove(i);
                }
            }
        }

        return (System.currentTimeMillis() - startTime); // Return time in milliseconds
    }

}
