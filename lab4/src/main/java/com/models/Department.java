package com.models;

/**
 * Класс представляет структуру отдела с уникальным идентификатором(id).
 */
public class Department {

    private static int ID_FACTORY = 1;

    private final int id;
    private String name;

    public Department(String name) {
        this.id = ID_FACTORY++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Department [id=%d, name=%s]", id, name);
    }

}
