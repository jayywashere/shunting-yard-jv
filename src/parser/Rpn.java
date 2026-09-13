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
                    String op = t.getValue();

                    if (isUnary(op)) {
                        addToOperators(t);
                        break;
                    }
                    
                    while (shouldPopOperator(op)) {
                        Token top = operators.removeLast();
                        addToOutput(top);
                    }
                    
                    addToOperators(t);
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
            
            if (top.getType() == Token.Type.LPAREN) {
                throw new IllegalArgumentException("Mismatched parentheses.");
            }

            addToOutput(top);
        }

        return output;
    }

    private boolean shouldPopOperator(String curOp) {
        if (operators.isEmpty()) {
            return false;
        }

        Token top = operators.getLast();

        if (top.getType() != Token.Type.OPERATOR) {
            return false;
        }

        String topOp = top.getValue();

        int topPrec = getPrecedence(topOp);
        int curPrec = getPrecedence(curOp);

        return topPrec > curPrec
            || (topPrec == curPrec
                && !isRightAssociative(curOp)
            );
    }

    private void addToOutput(Token token) {
        output.add(token);
    }

    private void addToOperators(Token token) {
        operators.add(token);
    }

    private boolean isUnary(String op) {
        return op.equals("u-") || op.equals("u+");
    }

    private boolean isRightAssociative(String op) {
        return op.equals("^")
            || isUnary(op);
    }

    private int getPrecedence(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/", "%" -> 2;
            case "u+", "u-" -> 3;
            case "^" -> 4;
            default -> -1;
        };
    }
}