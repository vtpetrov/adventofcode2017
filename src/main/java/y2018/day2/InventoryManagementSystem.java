package y2018.day2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static helper.InputLoader.*;

public class InventoryManagementSystem {

    private static final String INPUT_FILE_NAME = "year_2018/day2_input.txt";
    private static ArrayList<String> boxes = new ArrayList<>();
    private static int twos = 0;
    private static int threes = 0;
    private static long checksum = 0;

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2018    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 2 ===---     ");
        System.out.println("                   - Inventory Management System -     ");

        System.out.println("\n    ---=== Part 1 ===---     ");

        loadInput(INPUT_FILE_NAME, "");

        //read and store the input so it will be used for both part 1 and 2:
        while (getMainIn().hasNextLine()) {

            boxes.add(getMainIn().nextLine());

        }
        closeInput();

        partOne();


        long p2Start = new Date().getTime();
        System.out.println("\nP1 Duration: " + (p2Start - start) + "ms (" + (p2Start - start) / 1000 + "s)");

        System.out.println("=========================================================================================");

        partTwo();

        closeInput();


        long end = new Date().getTime();
        System.out.println("\nP2 Duration: " + (end - p2Start) + "ms (" + (end - p2Start) / 1000 + "s)");
        System.out.println("==========");
        System.out.println("\nTotal Duration: " + (end - start) + "ms (" + (end - start) / 1000 + "s)");

        System.out.println("\n:::END = " + end);
    }

    private static void partOne() {

        for(String box : boxes){
            Map<String, Integer> boxStats = new HashMap<>();

            for(int i = 0; i < box.length(); i++){
                boxStats.merge(String.valueOf(box.charAt(i)), 1, Integer::sum);
            }

            if(boxStats.containsValue(2)) twos++;
            if(boxStats.containsValue(3)) threes++;
        }

        checksum = twos * threes;


        System.out.println("\n    Part 1 solution:   the checksum for your list of box IDs= " + checksum);

    }

    private static void partTwo() {


        System.out.println("\n    Part 2 solution:   YYYYYYYYYYYY= [");
    }

}
