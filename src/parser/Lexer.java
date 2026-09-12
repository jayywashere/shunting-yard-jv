package parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final ArrayList<Token> tokens = new ArrayList<>();

    public List<Token> tokenize(Equation equation) {
        tokens.clear();

        StringBuilder builder = new StringBuilder();
        String expression = equation.getExpression();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                builder.append(c);
            } else {

                if (!builder.isEmpty()) {
                    String number = builder.toString();
                    builder.setLength(0);
    
                    addToken(Token.Type.NUMBER, number);
                }
                
                switch (c) {
                    case '+', '-', '*', '/', '%'
                        -> addToken(Token.Type.OPERATOR, String.valueOf(c));

                    case ' ' -> {}
                    
                    case '(' -> addToken(Token.Type.LPAREN, String.valueOf(c));
                    case ')' -> addToken(Token.Type.RPAREN, String.valueOf(c));

                    default // ? addToken(Token.Type.UNKNOWN, String.valueOf(c));
                        -> throw new IllegalArgumentException("Unknown character: " + c);
                }
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