package day11;

import java.util.Date;

import static helper.InputLoader.getMainIn;
import static helper.InputLoader.loadInput;

public class HexEd {

    //    private static final String INPUT_FILE_NAME = "day11_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);

        System.out.println("    ---=== Day 11 ===---     ");

        System.out.println("\n    ---=== Part 1 ===---     ");
        partOne();

        //        System.out.println("\n    ---=== Part 2 ===---     ");
//        partTwo();

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("Duration: " + (end - start) + " milliseconds");

    }

    /**
     * You have the path the child process took. Starting where he started, you need to determine the fewest number of steps required to reach him. (A "step" means to move from the hex you are in to any adjacent hex.)
     * <p>
     * For example:
     * <p>
     * ne,ne,ne is 3 steps away.
     * ne,ne,sw,sw is 0 steps away (back where you started).
     * ne,ne,s,s is 2 steps away (se,se).
     * se,sw,se,sw,sw is 3 steps away (s,s,sw).
     */
    private static void partOne() {

        loadInput(INPUT_FILE_NAME);
        String inputWhole;

        inputWhole = getMainIn().nextLine();


    }

}
