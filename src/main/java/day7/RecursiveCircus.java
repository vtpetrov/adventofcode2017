package day7;

import java.util.Date;
import java.util.List;

import static helper.InputLoader.*;

public class RecursiveCircus {

    //    private static final String INPUT_FILE_NAME = "day7_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";
    private static Towers towers;

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

    /**
     * What is the name of the bottom program
     */
    private static void partOne() {
        loadInput(INPUT_FILE_NAME);

        while (getMainIn().hasNextLine()) {
            towers.addTower(new Tower(getMainIn().nextLine()));
        }
        System.out.println("[" + towers.getTowers().size() + "] towers added:");
        System.out.println("towers = " + towers);

        List<Tower> holding = towers.getTowersHolding(true);
//        List<Tower> beingHold = towers.getTowers(holding, true, true);
//        List<Tower> notBeingHold = towers.getTowers(holding, true, false);


//        holding.retainAll(notBeingHold);

        System.out.println("holding = " + holding.size());

//        System.out.println("Part 2 solution:  hops to exit= " + hops);

        closeInput();

    }

    private static void partTwo() {

    }



}
