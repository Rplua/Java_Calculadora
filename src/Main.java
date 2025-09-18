import java.util.*;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        char operator;
        double result = 0.0;
        boolean hasResult = false;
        List<String> story = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Choose an operator: +, -, *, / or q to quit");
            operator = input.next().charAt(0);

            if (operator == 'q') {
                printHistory(story);
                System.out.println("Exiting calculator...");

                break;
            }

            if (!isValidOperator(operator)) {
                System.out.println("Invalid Operator!");
                continue;
            }

            double number1 = continueWithTheLastNum(hasResult, input, result);

            double number2 = readNumber(input, "Enter your second number:");

            try {
                double current = calculation(operator, number1, number2, story);
                System.out.println(number1 + " " + operator + " " + number2 + " = " + current);
                System.out.println("Last result: " + current);
                result = current;
                hasResult = true;
            } catch (ArithmeticException ae) {
                System.out.println("Error: " + ae.getMessage());
            }
        } while (true);

        input.close();
    }

    private static boolean isValidOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    private static double readNumber(Scanner input, String prompt) {
        while (true) {
            System.out.println(prompt);
            if (input.hasNextDouble()) {
                return input.nextDouble();
            } else {
                System.out.println("Error: debes ingresar un número.");
                input.next(); // descarta la basura
            }
        }
    }

    private static double calculation(char operator, double num1, double num2, List<String> story) {
        Map<Character, BiFunction<Double, Double, Double>> operation = new HashMap<>();

        operation.put('+', (a, b) -> a + b);
        operation.put('-', (a, b) -> a - b);
        operation.put('*', (a, b) -> a * b);
        operation.put('/', (a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });
        double result = operation.get(operator).apply(num1, num2);
        saveOperation(story, operator, num1, num2, result);
        return result;
    }


    private static double continueWithTheLastNum(boolean hasResult, Scanner input, double lastResult) {
        if (hasResult) {
            System.out.println("Do you want to use the last result (" + lastResult + ") as first number? (y/n)");
            char choice = input.next().charAt(0);
            if (choice == 'y' || choice == 'Y') {
                return lastResult;
            }
        }
        return readNumber(input, "Enter your first number:");
    }

    private static void saveOperation(List<String> story, char operator, double num1, double num2, double result) {
        String record = num1 + " " + operator + " " + num2 + " = " + result;
        story.add(record);
    }


    private static void printHistory(List<String> story) {
        System.out.println("History:");
        if (story.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (String record : story) {
            System.out.println(record);
        }
    }
}

