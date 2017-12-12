package day12;

import common.Program;
import common.Programs;

import java.util.Date;

import static helper.InputLoader.getMainIn;
import static helper.InputLoader.loadInput;

public class DigitalPlumber {

    private static final String INPUT_FILE_NAME = "day12_input.txt";
    //    private static final String INPUT_FILE_NAME = "debug.txt";
    private static Programs programs = new Programs();

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("    ---=== Day 12 ===---     ");
        System.out.println("\n    ---=== Part 1 ===---     ");
        partOne();

//        System.out.println("\n    ---=== Part 2 ===---     ");
//        partTwo();

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("Duration: " + (end - start) + " milliseconds");

    }

    /**
     * You walk through the village and record the ID of each program and the IDs with which it can communicate directly (your puzzle input). Each program has one or more programs with which it can communicate, and these pipes are bidirectional; if 8 says it can communicate with 11, then 11 will say it can communicate with 8.
     * <p>
     * You need to figure out how many programs are in the group that contains program ID 0.
     * <p>
     * For example, suppose you go door-to-door like a travelling salesman and record the following list:
     * <p>
     * 0 <-> 2
     * 1 <-> 1
     * 2 <-> 0, 3, 4
     * 3 <-> 2, 4
     * 4 <-> 2, 3, 6
     * 5 <-> 6
     * 6 <-> 4, 5
     */
    private static void partOne() {

        loadInput(INPUT_FILE_NAME);

        while (getMainIn().hasNextLine()) {
            programs.addProgram(new Program(getMainIn().nextLine()));
        }

        System.out.println("------------");
        System.out.println(programs.getPrograms());
//        programs.populateCommunicatingPrograms();

        int numberOfProgramsInZeroChain = 0;

        for (Program programToCheckForZeroChainApplice : programs.getPrograms()) {
            if (programs.isPartOfZeroChain(programToCheckForZeroChainApplice, programToCheckForZeroChainApplice.getId())) {
                numberOfProgramsInZeroChain++;
            }
        }

        System.out.println("\nnumberOfProgramsInZeroChain = " + numberOfProgramsInZeroChain);


    }

    private static void partTwo() {

    }
}
