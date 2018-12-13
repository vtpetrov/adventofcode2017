package y2018.day5;

import java.util.Date;

import static helper.InputLoader.*;

public class AlchemicalReduction {
    private static final String INPUT_FILE_NAME = "year_2018/day5_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";


    private static String originalPolymer;
    private static String resultingPolymer;

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2018    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 5 ===---     ");
        System.out.println("               - Alchemical Reduction -     ");

        System.out.println("\n    ---=== Part 1 ===---     ");

        loadInput(INPUT_FILE_NAME, "");
        originalPolymer = getMainIn().nextLine();
        resultingPolymer = originalPolymer;

        partOne();


        long p2Start = new Date().getTime();
        System.out.println("\nP1 Duration: " + (p2Start - start) + "ms (" + (p2Start - start) / 1000 + "s)");

        System.out.println("=========================================================================================");

        System.out.println("\n    ---=== Part 2 ===---     ");
        partTwo();

        closeInput();


        long end = new Date().getTime();
        System.out.println("\nP2 Duration: " + (end - p2Start) + "ms (" + (end - p2Start) / 1000 + "s)");
        System.out.println("==========");
        System.out.println("\nTotal Duration: " + (end - start) + "ms (" + (end - start) / 1000 + "s)");

        System.out.println("\n:::END = " + end);
    }

    private static void partOne() {
//        System.out.println("Solution 1: ----");
//        while (removeOneReaction(resultingPolymer)) {
//        }

        System.out.println("Solution 2: ------");
        removeAllReactions(originalPolymer);

        System.out.println("\n    Part 1 solution:   units remain after fully reacting the polymer = " + resultingPolymer.length());

    }

    private static void removeAllReactions(String inputPolymer) {
        StringBuilder inputSB = new StringBuilder(inputPolymer);

//        System.out.println("DEBUG inputPolymer = " + inputPolymer);

        for (int i = 0; i < inputSB.length() - 2; i++) {
//            System.out.println("LOOP: " + i + ", inputPolymer = " + inputPolymer);
            char curr = inputSB.charAt(i);
            char next = inputSB.charAt(i + 1);

            boolean currIsCapital = Character.isUpperCase(curr);
            boolean nextIsCapital = Character.isUpperCase(next);

            if (Character.toLowerCase(curr) == Character.toLowerCase(next)) {
                if (currIsCapital != nextIsCapital) { // react

                    inputSB.delete(i, i + 2);

                    if (i > 0) {
                        i -= 2;
                    } else {
                        i--;
                    }
                }
            }
        }

        resultingPolymer = inputSB.toString();
    }

    private static boolean removeOneReaction(String inputPolymer) {

        String resultingString;

        for (int i = 0; i < inputPolymer.length() - 2; i++) {
            char curr = inputPolymer.charAt(i);
            char next = inputPolymer.charAt(i + 1);

            boolean currIsCapital = Character.isUpperCase(curr);
            boolean nextIsCapital = Character.isUpperCase(next);

            if (Character.toLowerCase(curr) == Character.toLowerCase(next)) {
                if (currIsCapital != nextIsCapital) { // react
                    resultingString = inputPolymer.substring(0, i) + inputPolymer.substring(i + 2);
                    resultingPolymer = resultingString;
                    return true;
                }
            }
        }

        return false;

    }

    private static void partTwo() {


        System.out.println("\n    Part 2 solution:   YYYYYYYYYYYY= [");
    }
}
