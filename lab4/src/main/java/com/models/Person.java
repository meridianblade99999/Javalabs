package com.models;

/**
 * Класс представляет сущность человека с уникальными атрибутами, такими как идентификатор, имя, пол, отдел,
 * зарплата и дата рождения.
 * Основные характеристики:
 * - id: уникальный идентификатор человека
 * - name: имя человека
 * - gender: пол человека (перечисление {@code EGender})
 * - department: отдел, к которому относится человек (объект класса {@code Department})
 * - salary: зарплата человека
 * - birthday: дата рождения человека
 *
 * Основное назначение этого класса — представлять и обрабатывать структурированные данные о человеке.
 */
public class Person {

    private final int id;
    private String name;
    private EGender gender;
    private Department department;
    private int salary;
    private final String birthday;

    public Person(int id, String name, EGender gender, Department department, int salary, String birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EGender getGender() {
        return gender;
    }

    public Department getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Person [id=%d, name=%s, gender=%s, salary=%d, " +
                "birthday=%s, department.id=%d, department.name=%s]",
            id, name, gender.getName(), salary, birthday, department.getId(), department.getName());
    }

}