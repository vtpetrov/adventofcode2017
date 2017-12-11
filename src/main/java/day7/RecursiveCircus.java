package day7;

import java.util.Date;
import java.util.stream.Collectors;

import static helper.InputLoader.*;

public class RecursiveCircus {

    //    private static final String INPUT_FILE_NAME = "day7_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";
    private static Towers towers = new Towers();

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("    ---=== Day 7 ===---     ");
//        System.out.println("\n    ---=== Part 1 ===---     ");
//        partOne();
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

        Towers holdingTowers = new Towers(towers.getTowersHolding(true));
        Towers notHoldedTowers = new Towers(holdingTowers.getTowersBeingHold(false));

        if (notHoldedTowers.getTowers().size() != 1) {
            throw new Error("Invalid input: more than one bottom program!:\n" + notHoldedTowers);
        } else {
            System.out.println("\nPart 1 solution:  bottom program name= " + notHoldedTowers.getTowers().stream().map
                    (Tower::getName).collect(Collectors.toList()));
        }

        closeInput();

    }

    /**
     * Given that exactly one program is the wrong weight, what would its weight need to be to balance the entire tower?
     */
    private static void partTwo() {

        loadInput(INPUT_FILE_NAME);

        while (getMainIn().hasNextLine()) {
            towers.addTower(new Tower(getMainIn().nextLine()));
        }

        towers.populateHoldedTowers();

        Towers holdingTowers = new Towers(towers.getTowersHolding(true));

        for (Tower t : holdingTowers.getTowers()) {
            System.out.println("\n- t = " + t);

            Towers.calculateStackWeight(t);
        }

        System.out.println("\nMAIN holdingTowers.getTower(\"tknk\"): \n" + holdingTowers.getTower("tknk").getStackWeight());

        holdingTowers.findInbalancedDisks();

        System.out.println("Towers.imbalancedHolder = " + Towers.imbalancedHolder.getName());
        System.out.println("\nTowers.imbalancedPrograms = " + Towers.imbalancedPrograms.stream().map(Tower::getName).collect
                (Collectors.toList()));

        Towers.calcBalancingValue();

    }
}
