import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char operator;
        double number1, number2, result = 0;
        boolean hasResult = false;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Choose an operator: +, -, *, / or q to quit");
            operator = input.next().charAt(0);

            if (operator == 'q') {
                System.out.println("Exiting calculator...");
                break;
            }

            if (hasResult) {
                System.out.println("Do you want to use the last result (" + result + ") as first number? (y/n)");
                char choice = input.next().charAt(0);
                if (choice == 'y') {
                    number1 = result;
                } else {
                    number1 = readNumber(input, "Enter your first number:");
                }
            } else {
                number1 = readNumber(input, "Enter your first number:");
            }

            number2 = readNumber(input, "Enter your second number:");

            switch (operator) {
                case '+':
                    result = number1 + number2;
                    System.out.println(number1 + " + " + number2 + " = " + result);
                    break;
                case '-':
                    result = number1 - number2;
                    System.out.println(number1 + " - " + number2 + " = " + result);
                    break;
                case '*':
                    result = number1 * number2;
                    System.out.println(number1 + " * " + number2 + " = " + result);
                    break;
                case '/':
                    while (number2 != 0) {
                        result = number1 / number2;
                        System.out.println(number1 + " / " + number2 + " = " + result);
                    }
                    System.out.println("Is not possible to divide by 0");
                    break;
                default:
                    System.out.println("Invalid Operator!");
                    continue;
            }

            hasResult = true;

        } while (true);

        input.close();
    }

    private static double readNumber(Scanner input, String prompt) {
        while (true) {
            System.out.println(prompt);
            if (input.hasNextDouble()) {
                return input.nextDouble();
            } else {
                System.out.println("Error: debes ingresar un número.");
                input.next();
            }
        }
    }
}

