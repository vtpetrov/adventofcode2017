package y2018.day8;

import java.util.Date;

import static helper.InputLoader.closeInput;
import static helper.InputLoader.getMainIn;
import static helper.InputLoader.loadInput;

public class MemoryManeuver {

        private static final String INPUT_FILE_NAME = "year_2018/day8_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";
    private static int sumOfMetadataEntries;

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2018    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 8 ===---     ");
        System.out.println("                 - Memory Maneuver -     ");

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

        parseInputPartOne(getMainIn().nextLine());


        System.out.println("\n    Part 1 solution:   the sum of all metadata entries= " + sumOfMetadataEntries);

    }

    private static String parseInputPartOne(String s) {

        // read header:
        // - qty of child nodes
        int toIndex = s.indexOf(" ");
        int quantityOfChildNodes = Integer.valueOf(s.substring(0, toIndex));
        // recalc/reposition string and indexes
        s = s.substring(toIndex + 1);
        toIndex = s.indexOf(" ");

        // - qty of metadata entries
        int quantityOfMetadataEntries = Integer.valueOf(s.substring(0, toIndex));
        // recalc/reposition string and indexes
        s = s.substring(toIndex + 1);
        toIndex = s.indexOf(" ");

        // read nodes
        for (int n = 0; n < quantityOfChildNodes; n++) {
            s = parseInputPartOne(s);
        }


        toIndex = s.indexOf(" ");
        // read metadata

        for (int m = 0; m < quantityOfMetadataEntries; m++) {
            int metadataEntry;
            if (toIndex == -1) { // last entry in the sequence/file
                metadataEntry = Integer.valueOf(s);
            } else {
                metadataEntry = Integer.valueOf(s.substring(0, toIndex));
                s = s.substring(toIndex + 1);
                toIndex = s.indexOf(" ");
            }

            sumOfMetadataEntries += metadataEntry;
        }

        return s;

    }

    private static void partTwo() {


        System.out.println("\n    Part 2 solution:   YYYYYYYYYYYY= [");
    }
}
