package com.javalabs;

/**
 * Контейнер для элементов int на основе списка в виде массива
 */
public class IntList {

    /**
     * Массив для хранения элементов
     */
    private int[] data;
    /**
     * Индекс последнего элемента в массиве
     */
    private int index;

    /**
     * Конструктор по умолчанию, передает начальный размер равный 1
     */
    public IntList() {
        this(1);
    }

    /**
     * Вызывает IllegalArgumentException, если initialCapacity<1
     * @param initialCapacity - начальный размер массива для элементов
     */
    public IntList(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity must be >= 1");
        }
        data = new int[initialCapacity];
        index = 0;
    }

    /**
     * Добавление элемента в список
     * @param value - добавляемый элемент
     */
    public void add(int value) {
        expandIfFull();
        data[index] = value;
        index++;
    }

    /**
     * Получение элемента списка
     * Вызывает IllegalArgumentException, если полученный индекс <0 или выходит за границы текущего размера списка
     * @param index - индекс получаемого элемента
     * @return элемент списка
     */
    public int get(int index) {
        if (index < 0 || index >= this.index) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return data[index];
    }

    /**
     * Удаление элемента из списка (по индексу)
     * Вызывает IllegalArgumentException, если полученный индекс <0 или выходит за границы текущего размера списка
     * @param removeIndex - удаляемый индекс
     * @return элемент, удаленный по заданному индексу
     */
    public int remove(int removeIndex) {
        if (removeIndex < 0 || removeIndex >= index) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        int value = data[removeIndex];
        for (int i = removeIndex; i < index - 1; i++) {
            data[i] = data[i + 1];
        }
        index--;
        return value;
    }

    /**
     * @return текущий размер списка
     */
    public int size() {
        return index;
    }

    /**
     * Проверка и расширение массива для хранения элементов списка при его переполнении
     */
    private void expandIfFull() {
        if (index == data.length) {
            expand();
        }
    }

    /**
     * Расширение массива для хранения элементов списка
     */
    private void expand() {
        int newCapacity = data.length * 2;
        int[] newArray = new int[newCapacity];
        System.arraycopy(data, 0, newArray, 0, data.length);
        this.data = newArray;
    }

    /**
     * @return true - массив пустой, false - иначе
     */
    public boolean isEmpty() {
        return index == 0;
    }

    /**
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < index; i++) {
            sb.append(data[i]);
            if (i < index - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Очищает список путем установки текущей позиции в массиве элементов на 0
     */
    public void clear() {
        index = 0;
    }

}
