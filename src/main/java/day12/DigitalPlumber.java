package day12;

import java.util.Date;

public class DigitalPlumber {

    //    private static final String INPUT_FILE_NAME = "day12_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("    ---=== Day 12 ===---     ");
        System.out.println("\n    ---=== Part 1 ===---     ");
        partOne();

        System.out.println("\n    ---=== Part 2 ===---     ");
        partTwo();

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("Duration: " + (end - start) + " milliseconds");

    }
}
