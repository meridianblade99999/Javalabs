package com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ExpressionCalculatorTest {

    @org.junit.Test
    public void testSimpleExpression() {
        String expression = "2 * 3 + 4";
        double expected = 10.0;
        double result = ExpressionCalculator.calculate(expression, null);
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testSimpleBrackets() {
        String expression = "(7 + 3) * 2 - (4 * 2) / 8";
        double expected = 19.0;
        double result = ExpressionCalculator.calculate(expression, null);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testBrackets() {
        String expression = "(((3 + 2) * 3 + 5) / 10 ) + 1.1";
        double expected = 3.1;
        double result = ExpressionCalculator.calculate(expression, null);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testVariables() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("hours", 10.0);
        variables.put("min", 5.0);

        String expression = "(hours * 60) + min";
        double expected = 605;
        double result = ExpressionCalculator.calculate(expression, variables);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testFunction() {
        String expression = "sin(90) + cos(0)*sqrt(9)";
        double expected = 4;
        double result = ExpressionCalculator.calculate(expression, null);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testVarAndFunc() {
        Map<String, Double> variables = new HashMap<>();
        variables.put("deg", 90.0);

        String expression = "sin(deg) * 5 + cos(deg)";
        double expected = 5;
        double result = ExpressionCalculator.calculate(expression, variables);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testInvalidExpression() {
        String expression = "(2 + 3";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExpressionCalculator.calculate(expression, null);
        });

        assertEquals("Некорректное выражение", exception.getMessage());
    }

    @Test
    public void testUndefinedVar() {
        String expression = "2 + a";
        Map<String, Double> variables = new HashMap<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExpressionCalculator.calculate(expression, variables);
        });

        assertEquals("Неизвестная переменная: a", exception.getMessage());
    }

    @Test
    public void testUndefinedFunc() {
        String expression = "sins(2) + 5";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExpressionCalculator.calculate(expression, null);
        });

        assertEquals("Неизвестная функция: sins", exception.getMessage());
    }

}