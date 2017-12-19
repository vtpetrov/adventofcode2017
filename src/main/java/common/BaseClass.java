package common;

import java.util.Date;

public class BaseClass {

    public static void main(String day) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 8 ===---     ");
        System.out.println("\n    ---=== Part 1 and 2 ===---     ");

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("\nDuration: " + (end - start) + " milliseconds");

    }
}
