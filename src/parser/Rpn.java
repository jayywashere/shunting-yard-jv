package parser;

import java.util.ArrayList;
import java.util.List;

public class Rpn {
    private final ArrayList<Token> output = new ArrayList<>();
    private final ArrayList<Token> operators = new ArrayList<>();

    public List<Token> convert(List<Token> tokens) {
        output.clear();
        operators.clear();

        for (int i = 0; i < tokens.size(); i++) {
            Token t = tokens.get(i);

            if (t.getType() != null) switch (t.getType()) {
                case NUMBER -> addToOutput(t);

                case OPERATOR -> {
                    while (!operators.isEmpty()
                            && getPrecedence(operators.get(operators.size() - 1).getValue())
                            >= getPrecedence(t.getValue())) {
                        
                        Token top = operators.removeLast();
                        addToOutput(top);
                    }   addToOperators(t);
                }

                case LPAREN -> addToOperators(t);

                case RPAREN -> {
                    while (!operators.isEmpty()
                            && operators.get(operators.size() - 1).getType()
                            != Token.Type.LPAREN) {
                        
                        Token top = operators.removeLast();
                        addToOutput(top);
                    }
                    
                    if (operators.isEmpty()) {
                        throw new IllegalArgumentException("Mismatched parentheses.");
                    }

                    operators.removeLast();
                }

                default -> {
                }
            }
        }

        while (!operators.isEmpty()) {
            Token top = operators.removeLast();
            addToOutput(top);
        }

        return output;
    }

    private void addToOutput(Token token) {
        output.add(token);
    }

    private void addToOperators(Token token) {
        operators.add(token);
    }

    private int getPrecedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            default -> -1;
        };
    }
}