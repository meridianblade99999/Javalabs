package com;

/**
 * Представляет доступные математические функции
 * Каждая функция имеет имя и метод apply для выполнения операции
 */
public enum Function {
    /**
     * Функция синуса
     * Принимает значение в градусах и возвращает синус этого значения
     */
    SIN("sin") {
        @Override
        public double apply(double value) {
            return Math.sin(Math.toRadians(value));
        }
    },
    /**
     * Функция косинуса
     * Принимает значение в градусах и возвращает косинус этого значения
     */
    COS("cos") {
        @Override
        public double apply(double value) {
            return Math.cos(Math.toRadians(value));
        }
    },
    /**
     * Функция квадратного корня
     * Принимает неотрицательное число и возвращает его квадратный корень
     * Выбрасывает ошибку, если передано отрицательное число
     */
    SQRT("sqrt") {
        @Override
        public double apply(double value) {
            if (value < 0) {
                throw new IllegalArgumentException("Корень из отрицательного числа");
            }
            return Math.sqrt(value);
        }
    };

    private final String name;

    /**
     * Конструктор перечисления
     *
     * @param name Имя функции ("sin", "cos", "sqrt")
     */
    Function(String name) {
        this.name = name;
    }

    /**
     * Возвращает имя функции
     *
     * @return Имя функции в виде строки
     */
    public String getName() {
        return name;
    }

    /**
     * Абстрактный метод для выполнения операции функции
     *
     * @param value Значение, к которому применяется функция
     * @return Результат применения функции
     */
    public abstract double apply(double value);

    /**
     * Возвращает функцию по её имени.
     *
     * @param name Имя функции
     * @return Соответствующий объект EFunction
     * @throws IllegalArgumentException Если функция с указанным именем не найдена
     */
    public static Function fromName(String name) {
        for (Function func : values()) {
            if (func.getName().equals(name)) {
                return func;
            }
        }
        throw new IllegalArgumentException("Неизвестная функция: " + name);
    }

}