package app;

import calculator.Calculator;
import java.util.Scanner;
import parser.Equation;

public class App {
    public void run(Scanner scanner) {
        System.out.println("Shunting Yard Calculator");
        System.out.println("Type an expression to calculate, or \"exit\" to quit.");

        while (true) {
            System.out.print("\n> ");

            try {
                String prompt = scanner.nextLine().trim();

                if (prompt.equalsIgnoreCase("exit")) {
                    break;
                }

                if (prompt.isEmpty()) {
                    continue;
                }

                Equation equation = new Equation(prompt);
                Calculator calc = new Calculator();

                double result = calc.calculate(equation);
                System.out.printf("%,.2f%n", result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }
}