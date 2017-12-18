package day9;

import java.util.Date;

import static helper.InputLoader.*;

public class StreamProcessing {
    private static final String INPUT_FILE_NAME = "day9_input.txt";
    //    private static final String INPUT_FILE_NAME = "debug.txt";
    private static final String GROUP_START = "{";
    private static final String GROUP_END = "}";
    private static final String GARBAGE_START = "<";
    private static final String GARBAGE_END = ">";
    private static final String SKIP = "!";
    private static long groups = 0;
    private static long totalScore = 0;
    private static boolean inStream;
    private static boolean inGarbage;
    private static boolean skipNext;

    private static void switchContext() {
//        garbage -> stream
        inGarbage = inStream;
        inStream = !inGarbage;
    }

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 9 ===---     ");
        System.out.println("                - StreamProcessing -     ");
        System.out.println("\n    ---=== Part 1 ===---     ");


        loadInput(INPUT_FILE_NAME, "");

        inStream = true;
        inGarbage = false;

        String ch = null;
        String skipped = null;
        int cost = 1;
//        {{<a!>},{<a!>},{<a!>},{<ab>}}
        while (getMainIn().hasNext()) {

            if (!skipNext) { // process:
                ch = getMainIn().next();

                if (inGarbage) { // in garbage
                    if (ch.equals(SKIP)) skipNext = true;
                } else { // in stream
                    if (ch.equals("<")) switchContext();
                    if (ch.equals(">")) switchContext();
                    if (ch.equals("{")) {
                        groups++;
                        totalScore += cost;
                        cost++;
                    }
                    if (ch.equals("}")) {
                        cost--;
                    }
                }

            } else { // skip
                skipped = getMainIn().next();
                skipNext = false;
            }

            System.out.println("ch = " + ch);
            System.out.println("skipNext = " + skipNext);
            System.out.println("skipped = " + skipped);

        }

        System.out.println("groups      = " + groups);
        System.out.println("totalScore  = " + totalScore);

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("\nDuration: " + (end - start) / 1000 + " s");

        closeInput();

    }
}
