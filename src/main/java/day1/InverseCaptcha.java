package day1;

import static helper.InputLoader.getMainIn;
import static helper.InputLoader.loadInput;

public class InverseCaptcha {

    private static final String INPUT_FILE_NAME = "day1_input.txt";

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");

        System.out.println("    ---=== Day 1 ===---     ");
        partOne();

    }

    /**
     * The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. The list is circular, so the digit after the last digit is the first digit in the list.
     * <p>
     * For example:
     * <p>
     * 1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
     * 1111 produces 4 because each digit (all 1) matches the next.
     * 1234 produces 0 because no digit matches the next.
     * 91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
     *
     * @throws Throwable
     */
    private static void partOne() throws Throwable {

        loadInput(INPUT_FILE_NAME);

        String input = getMainIn().nextLine();
        int MAX_INDEX = input.length() - 1;
        long sum = 0;


        for (int i = 0; i < MAX_INDEX - 1; i++) {
            if ((int) input.charAt(i) == (int) input.charAt(i + 1)) {
                sum += Character.getNumericValue(input.charAt(i));
            }
        }

        if ((int) input.charAt(MAX_INDEX) == (int) input.charAt(0)) { // last digit, first digit:
            sum += Character.getNumericValue(input.charAt(MAX_INDEX));
        }

        System.out.println("Part 1 solution:  sum = " + sum);

    }

}
