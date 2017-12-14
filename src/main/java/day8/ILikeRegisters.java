package day8;

import java.util.*;

import static helper.InputLoader.*;

public class ILikeRegisters {

    //    private static final String INPUT_FILE_NAME = "day8_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";
    static Set<String> registerNames = new TreeSet<>();
    static List<Instruction> instructions = new ArrayList<>();
    static Map<String, Register> registers = new TreeMap<>();

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 8 ===---     ");
        System.out.println("\n    ---=== Part 1 ===---     ");
        partOne();

//        System.out.println("\n    ---=== Part 2 ===---     ");
//        partTwo();

        long end = new Date().getTime();
        System.out.println("\n:::END = " + end);
        System.out.println("\nDuration: " + (end - start) + " milliseconds");

    }

    /**
     * b inc 5 if a > 1
     * a inc 1 if b < 5
     * c dec -10 if a >= 1
     * c inc -20 if c == 10
     */
    private static void partOne() {

        loadInput(INPUT_FILE_NAME);

        while (getMainIn().hasNextLine()) {
            String nextLine = getMainIn().nextLine();
            String name = nextLine.substring(0, nextLine.indexOf(" "));
            registerNames.add(name);
        }

        System.out.println("registerNames= " + registerNames.size() + " : " + registerNames);

        // initialize Registers:
        for (String name : registerNames) {
            registers.put(name, new Register(name));
        }

        System.out.println("register= " + registers.size() + " : " + registers);


        System.out.println("Part 1 solution:");

        closeInput();
    }

    private static void partTwo() {
        loadInput(INPUT_FILE_NAME);

        while (getMainIn().hasNextLine()) {

        }

        System.out.println("Part 2 solution:");

        closeInput();


    }
}
