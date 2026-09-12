package parser;

import calculator.Calculator;

public class Equation {
    private final String expression;

    public Equation(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public static double evaluate(Equation equation) {
        Calculator calc = new Calculator();
        double result = calc.calculate(equation);

        return result;
    }
}