import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите операцию: ");
        String input = in.nextLine();
        in.close();

        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new RuntimeException("Invalid input");
        }

        String operand1 = parts[0];
        String action = parts[1];
        String operand2 = parts[2];

        if (isDigit(operand1) && isDigit(operand2)) {
            int num1 = Integer.parseInt(operand1);
            int num2 = Integer.parseInt(operand2);
            if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
                throw new RuntimeException("Invalid number");
            }
            int result = calculate(num1, num2, action);
            System.out.println(result);

        } else {
            int num1 = new RomanNumber(operand1).toInt();
            int num2 = new RomanNumber(operand2).toInt();
            int result = calculate(num1, num2, action);
            System.out.println(new RomanNumber(result).toStr());
        }
    }

    private static int calculate(int num1, int num2, String action) {
        switch (action) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return  num1 * num2;
            case "/": return num1 / num2;
            default: throw new RuntimeException("Invalid action");
        }
    }

    private static boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class RomanNumber {
    private final int num;

    private final static int[] numbers = { 1000, 900, 500, 400, 100, 90, 50,
                                            40, 10, 9, 5, 4, 1 };
    private final static String[] letters = { "M", "CM", "D", "CD", "C", "XC", "L",
                                            "XL", "X", "IX", "V", "IV", "I" };

    RomanNumber(int arabic) {
        this.num = arabic;
    }

    RomanNumber(String roman) {
        this.num = this.letterToNumber(roman);
    }

    private int letterToNumber(String letter) {
        switch (letter) {
            case "X": return 10;
            case "IX": return 9;
            case "VIII": return 8;
            case "VII": return 7;
            case "VI": return 6;
            case "V": return 5;
            case "IV": return 4;
            case "III": return 3;
            case "II": return 2;
            case "I": return 1;
            default: throw new RuntimeException("Invalid letter");
        }
    }

    String toStr() {
        StringBuilder roman = new StringBuilder();
        int current = this.num;

        for (int i = 0; i < numbers.length; i++) {
            while (current >= numbers[i]) {
                roman.append(letters[i]);
                current -= numbers[i];
            }
        }
        return roman.toString();
    }

    int toInt() {
        return this.num;
    }
}
