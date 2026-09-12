package calculator;

import evaluator.Evaluator;
import java.util.List;
import parser.Equation;
import parser.Lexer;
import parser.Rpn;
import parser.Token;

public class Calculator {
    public double calculate(Equation equation) {
        // * Start

        // * #1 - Lexer
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize(equation);

        // * #2 - RPN
        Rpn rpn = new Rpn();
        List<Token> rpnTokens = rpn.convert(tokens);

        // * #3 - Evaluator
        Evaluator evaluator = new Evaluator();
        double result = evaluator.evaluate(rpnTokens);

        // * Voila
        return result;

        // * End
    }
}