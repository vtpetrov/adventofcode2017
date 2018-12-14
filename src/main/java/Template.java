import java.util.Date;

import static helper.InputLoader.closeInput;
import static helper.InputLoader.loadInput;

public class Template {

    private static final String INPUT_FILE_NAME = "year_201x/dayXX_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   201x    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day XX ===---     ");
        System.out.println("                  - XXXXXXXXX -     ");

        System.out.println("\n    ---=== Part 1 ===---     ");

        loadInput(INPUT_FILE_NAME, "");
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



        System.out.println("\n    Part 1 solution:   XXXXXX= [" );

    }

    private static void partTwo() {


        System.out.println("\n    Part 2 solution:   YYYYYYYYYYYY= [");
    }

}