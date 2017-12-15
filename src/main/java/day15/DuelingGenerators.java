package day15;

import java.util.Date;


public class DuelingGenerators {
    private static final String INPUT_FILE_NAME = "day8_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";

    private static final int A_INITIAL_VALUE = 634;
    private static final int B_INITIAL_VALUE = 301;
    private static final long MULTIPLIER_A = 16807;
    private static final long MULTIPLIER_B = 48271;
    private static final long DIVIDER = 2147483647;
    private static final int CYCLES = 40000000;
    //    private static final int JUDGES_MAX_VALUE = 5000000;
    private static final int JUDGES_MAX_VALUE = 100;
    private static long generatorA = A_INITIAL_VALUE;
    private static long generatorB = B_INITIAL_VALUE;
    private static int judges = 0;
    private static int matches = 0;


    /**
     * -Gen. A--  --Gen. B--
     * 1092455   430625591
     * 1181022009  1233683848
     * 245556042  1431495498
     * <p>
     * In binary, these pairs are (with generator A's value first in each pair):
     * <p>
     * 00000000000100001010101101100111
     * 00011001101010101101001100110111
     * <p>
     * 01000110011001001111011100111001
     * 01001001100010001000010110001000
     * <p>
     * 00001110101000101110001101001010
     * 01010101010100101110001101001010
     * <p>
     * <p>
     * To create its next value, a generator will take the previous value it produced,
     * multiply it by a factor (generator A uses 16807; generator B uses 48271),
     * and then keep the remainder of dividing that resulting product by 2147483647.
     * That final remainder is the value it produces next.
     *
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 15 ===---     ");
        System.out.println("\n    ---=== Part 1  ===---     \n");

        for (int i = 1; i <= CYCLES; i++) {

            if (((generatorA ^ generatorB) & 65535) == 0) {
                matches++;
            }

            generatorA = (generatorA * MULTIPLIER_A) % DIVIDER;
            generatorB = (generatorB * MULTIPLIER_B) % DIVIDER;

        }

        System.out.println("    Part 1:   matches = " + matches);

        // Start part 2
        generatorA = A_INITIAL_VALUE;
        generatorB = B_INITIAL_VALUE;

        while (judges <= JUDGES_MAX_VALUE) {

            if (judge()) {
                judges++;
                System.out.println("judges = " + judges);
            }

            generateAp2();
            generateBp2();

        }

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("\nDuration: " + (end - start) + " milliseconds");

    }

    private static boolean judge() {

        if (((generatorA ^ generatorB) & 65535) == 0) {
            return true;
        }
        return false;
    }

    private static void generateAp2() {

        do {
            generatorA = (generatorA * MULTIPLIER_A) % DIVIDER;
        } while ((generatorA % 4) != 0);

    }

    private static void generateBp2() {

        do {
            generatorB = (generatorB * MULTIPLIER_B) % DIVIDER;
        } while ((generatorA % 8) != 0);

    }
}
