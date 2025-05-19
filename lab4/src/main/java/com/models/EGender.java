package com.models;

/**
 * Перечисление, представляющее пол.
 * Содержит два значения: MALE (мужской пол) и FEMALE (женский пол).
 */
public enum EGender {
    MALE("Male"), FEMALE("Female");

    private final String name;

    EGender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EGender getGender(String name) {
        for (EGender gender : EGender.values()) {
            if (gender.getName().equals(name)) {
                return gender;
            }
        }
        throw new IllegalArgumentException(name + " is not a valid gender");
    }

    @Override
    public String toString() {
        return name;
    }

}
