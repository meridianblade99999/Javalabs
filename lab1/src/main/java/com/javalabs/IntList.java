package com.javalabs;

public class IntList {

    private int[] data;
    private int index;

    public IntList() {
        this(1);
    }

    public IntList(int initialCapacity) {
        data = new int[initialCapacity];
        index = 0;
    }

    public void add(int value) {
        expandIfFull();
        data[index] = value;
        index++;
    }

    public int get(int index) {
        return data[index];
    }

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

    public int size() {
        return index;
    }

    private void expandIfFull() {
        if (index == data.length) {
            expand();
        }
    }

    private void expand() {
        int newCapacity = data.length * 2;
        int[] newArray = new int[newCapacity];
        System.arraycopy(data, 0, newArray, 0, data.length);
        this.data = newArray;
    }

    public boolean isEmpty() {
        return index == 0;
    }

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

    public void clear() {
        index = 0;
    }

}
