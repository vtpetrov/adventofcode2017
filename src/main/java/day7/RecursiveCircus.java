package day7;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecursiveCircus {

    private static final String INPUT_FILE_NAME = "day7_input.txt";
    //    private static final String INPUT_FILE_NAME = "debug.txt";
    private static List<Integer[]> patternHistory = new ArrayList<>();
    private static int firstIndex;

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);

        System.out.println("    ---=== Day 7 ===---     ");
        System.out.println("\n    ---=== Part 1 ===---     ");
        partOne();
        System.out.println("\n    ---=== Part 2 ===---     ");
        partTwo();

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("Duration: " + (end - start) + " milliseconds");

    }

}
