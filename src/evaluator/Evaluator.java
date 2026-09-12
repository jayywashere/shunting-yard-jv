package evaluator;

import java.util.ArrayList;
import java.util.List;
import parser.Token;

public class Evaluator {
    private final ArrayList<Double> stack = new ArrayList<>();

    public double evaluate(List<Token> tokens) {
        stack.clear();

        for (int i = 0; i < tokens.size(); i++) {
            Token t = tokens.get(i);

            if (t.getType() != null) switch (t.getType()) {
                case NUMBER -> addNumber(t);
                case OPERATOR -> applyOperator(t);

                // * Warning Silencers
                case LPAREN -> {}
                case RPAREN -> {}
                // ? case UNKNOWN -> {}
            }
        }

        return stack.getLast();
    }

    private void addNumber(Token token) {
        if (token.getType() != Token.Type.NUMBER) {
            throw new IllegalArgumentException(
                "Expected a NUMBER token, got: " + token.getType() + " with value: " + token.getValue()
            );
        }

        String value = token.getValue();
        double num = Double.parseDouble(value);

        stack.add(num);
    }

    private void applyOperator(Token token) {
        if (token.getType() != Token.Type.OPERATOR) {
            throw new IllegalArgumentException(
                "Expected an OPERATOR token, got: " + token.getType() + " with value: " + token.getValue()
            );
        }

        double right = stack.removeLast();
        double left = stack.removeLast();

        double result = switch (token.getValue()) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> {
                if (right == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }

                yield left / right;
            }
            case "%" -> left % right;

            default -> throw new IllegalArgumentException("Unknown operator: " + token.getValue());
        };

        stack.add(result);
    }
}