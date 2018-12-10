package y2018.day1;

import java.util.Date;

import static helper.InputLoader.closeInput;
import static helper.InputLoader.getMainIn;
import static helper.InputLoader.loadInput;

public class ChronalCalibration {

    private static final String INPUT_FILE_NAME = "y2018-day1_input.txt";

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2018    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 1 ===---     ");
        System.out.println("                   - Chronal Calibration -     ");

        System.out.println("\n    ---=== Part 1 ===---     ");

        loadInput(INPUT_FILE_NAME, "");
        partOne();

        System.out.println("\n    Part 1 solution:   XXXXXX= [" );


        long p2Start = new Date().getTime();
        System.out.println("\nP1 Duration: " + (p2Start - start) + "ms (" + (p2Start - start) / 1000 + "s)");

        System.out.println("=========================================================================================");

        partTwo();
        System.out.println("\n    Part 2 solution:   YYYYYYYYYYYY= [");

        closeInput();


        long end = new Date().getTime();
        System.out.println("\nP2 Duration: " + (end - p2Start) + "ms (" + (end - p2Start) / 1000 + "s)");
        System.out.println("\nTotal Duration: " + (end - start) + "ms (" + (end - start) / 1000 + "s)");

        System.out.println("\n:::END = " + end);
    }

    private static void partOne() {

        long freq = 0;

        while (getMainIn().hasNextLine()){

            freq += getMainIn().nextInt();

        }

    }

    private static void partTwo() {

    }

}
