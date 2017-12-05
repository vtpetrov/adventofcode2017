package day5;

import java.util.Arrays;

import static helper.InputLoader.*;

public class TwistyTrampolines {

    private static final String INPUT_FILE_NAME = "day5_input.txt";

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");

        System.out.println("    ---=== Day 5 ===---     ");
        partOne();
        partTwo();

    }

    private static void partOne() {
        loadInput(INPUT_FILE_NAME);
        int l = 0;
        int[] hops = new int[1070];
        int hop = 0;


        while (getMainIn().hasNextLine()) {
            hops[l] = Integer.valueOf(getMainIn().nextLine());
            l++;
        }
        System.out.println("Initial state= " + Arrays.toString(hops));

        boolean found = false;
        int i = 0;
        int currentElem = 0;
        do {
            try {
                currentElem = hops[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                found = true;
                continue;
            }
            hops[i]++;
            i += currentElem;
            hop++;
        } while (!found);

        System.out.println("Final state= " + Arrays.toString(hops));

        System.out.println("\nPart 1 solution:  hops to exit= " + hop);

        closeInput();

    }

    private static void partTwo() {

    }
}
