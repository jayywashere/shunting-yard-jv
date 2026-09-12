package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            App app = new App();
            app.run(scanner);
        }
    }
}