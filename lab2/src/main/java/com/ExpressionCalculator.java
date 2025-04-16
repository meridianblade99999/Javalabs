package com;

import java.util.Map;
import java.util.Stack;

/**
 * Реализует методы для вычисления математических выражений,
 * содержащих числа, переменные, операторы и функции
 */
public class ExpressionCalculator {

    /**
     * Проверяет, существует ли функция с указанным именем
     *
     * @param input название функции
     * @return true, если функция существует, false не существует
     */
    public static boolean containsFunction(String input) {
        try {
            Function.fromName(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли символ оператором (+, -, *, /)
     *
     * @param c Символ для проверки
     * @return true, если символ является оператором, false не является
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Проверяет валидность выражения
     *
     * @param tokens Выражение в виде набора символов
     * @return true, если выражение корректно, false не корректно
     */
    private static boolean isValid(char[] tokens) {
        Stack<Character> stack = new Stack<>();

        for (char token : tokens) {
            if (token == ' ')
                continue;
            if (token == '(') {
                stack.push(token);
            } else if (token == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (!Character.isDigit(token) && !isOperator(token) && token != '.'
                    && !Character.isLetter(token)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Вычисляет значение математического выражения
     *
     * @param expression Математическое выражение в виде строки
     * @param variables  Объект с переменными, где ключ - имя переменной, значение - её числовое значение
     * @return Результат вычисления выражения
     * @throws IllegalArgumentException Если выражение некорректно или содержит неизвестные переменные
     */
    public static double calculate(String expression, Map<String, Double> variables) {
        char[] tokens = expression.toCharArray();

        if (!isValid(tokens)) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }

            if (Character.isDigit(tokens[i])) {
                StringBuilder num = new StringBuilder();

                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    num.append(tokens[i++]);
                }

                values.push(Double.parseDouble(num.toString()));
                i--;
            } else if (Character.isLetter(tokens[i])) {
                StringBuilder varName = new StringBuilder();
                boolean isFunc = false;

                while (i < tokens.length && (Character.isLetter(tokens[i]) || tokens[i] == '(')) {
                    if (tokens[i] == '(') {
                        isFunc = true;
                        i++;
                        break;
                    } else {
                        varName.append(tokens[i++]);
                    }
                }

                if (isFunc) {
                    String funcName = varName.toString();
                    StringBuilder num = new StringBuilder();

                    while (i < tokens.length && tokens[i] != ')') {
                        num.append(tokens[i++]);
                    }

                    String arg = num.toString();

                    Function func = Function.fromName(funcName);

                    try {
                        double value = Double.parseDouble(arg);
                        values.push(func.apply(value));
                    }
                    catch (NumberFormatException e) {
                        if (!variables.containsKey(arg)) {
                            throw new IllegalArgumentException("Неизвестная переменная: " + arg);
                        }
                        double variableValue = variables.get(arg);
                        values.push(func.apply(variableValue));
                    }
                } else {
                    String variable = varName.toString();
                    if (!variables.containsKey(variable)) {
                        throw new IllegalArgumentException("Неизвестная переменная: " + variable);
                    }

                    values.push(variables.get(variable));
                    i--;
                }
            } else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }

                operators.pop();
            } else if (isOperator(tokens[i])) {
                while (!operators.empty() && getPrecedence(tokens[i]) <= getPrecedence(operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }

                operators.push(tokens[i]);
            }
        }

        while (!operators.empty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }


    /**
     * Возвращает приоритет оператора
     *
     * @param operator Оператор (+, -, *, /)
     * @return Приоритет оператора (1 для + и -, 2 для * и /)
     */
    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Применяет оператор к двум значениям
     *
     * @param operator Оператор (+, -, *, /)
     * @param a Первый операнд
     * @param b Второй операнд
     * @return Результат применения оператора
     * @throws ArithmeticException Деление на ноль
     */
    private static double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }

}