package parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final ArrayList<Token> tokens = new ArrayList<>();

    public List<Token> tokenize(Equation equation) {
        tokens.clear();

        StringBuilder builder = new StringBuilder();
        String expression = equation.getExpression();

        boolean expectOperand = true;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                builder.append(c);
                continue;
            }

            if (!builder.isEmpty()) {
                String number = builder.toString();
                builder.setLength(0);

                addToken(Token.Type.NUMBER, number);
                expectOperand = false;
            }

            switch (c) {
                case '+', '-' -> {
                    if (expectOperand) {
                        addToken(
                            Token.Type.OPERATOR,
                            c == '+' ? "u+" : "u-"
                        );
                    } else {
                        addToken(Token.Type.OPERATOR, String.valueOf(c));
                    }

                    expectOperand = true;
                }

                case '*', '/', '%', '^' -> {
                    addToken(Token.Type.OPERATOR, String.valueOf(c));
                    expectOperand = true;
                }

                case '(' -> {
                    addToken(Token.Type.LPAREN, String.valueOf(c));
                    expectOperand = true;
                }

                case ')' -> {
                    addToken(Token.Type.RPAREN, String.valueOf(c));
                    expectOperand = false;
                }

                case ' ' -> {}

                default ->
                    throw new IllegalArgumentException(
                        "Unknown character: " + c
                    );
            }
        }

        if (!builder.isEmpty()) {
            String remainingNumber = builder.toString();
            builder.setLength(0);

            addToken(Token.Type.NUMBER, remainingNumber);
        }

        return tokens;
    }

    private void addToken(Token.Type type, String value) {
        tokens.add(new Token(type, value));
    }
}