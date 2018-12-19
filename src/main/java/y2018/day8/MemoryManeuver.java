package y2018.day8;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static helper.InputLoader.*;

public class MemoryManeuver {

    //    private static final String INPUT_FILE_NAME = "year_2018/day8_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";
    private static int sumOfMetadataEntries;
    private static int sumOfNodeValues;
    private static List<MyNode> nodes;


    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2018    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 8 ===---     ");
        System.out.println("                 - Memory Maneuver -     ");

        System.out.println("\n    ---=== Part 1 ===---     ");

        loadInput(INPUT_FILE_NAME, "");
        partOne();
        closeInput();


        long p2Start = new Date().getTime();
        System.out.println("\nP1 Duration: " + (p2Start - start) + "ms (" + (p2Start - start) / 1000 + "s)");

        System.out.println("=========================================================================================");
        System.out.println("\n    ---=== Part 2 ===---     ");


        loadInput(INPUT_FILE_NAME, "");
        partTwo();
        closeInput();


        long end = new Date().getTime();
        System.out.println("\nP2 Duration: " + (end - p2Start) + "ms (" + (end - p2Start) / 1000 + "s)");
        System.out.println("==========");
        System.out.println("\nTotal Duration: " + (end - start) + "ms (" + (end - start) / 1000 + "s)");

        System.out.println("\n:::END = " + end);
    }

    private static void partOne() {

        solutionP1(getMainIn().nextLine());


        System.out.println("\n    Part 1 solutionP1:   the sum of all metadata entries= " + sumOfMetadataEntries);

    }

    private static String solutionP1(String s) {

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

        // read nodes
        for (int n = 0; n < quantityOfChildNodes; n++) {
            s = solutionP1(s);
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

        solutionP2(getMainIn().nextLine());

        System.out.println("\n    Part 2 solutionP1:   YYYYYYYYYYYY= [");
    }

    private static void solutionP2(String s) {

        nodes = new ArrayList<>();
        parseDataP2(s, 0, -1);

        System.out.println("END nodes = \n" + nodes);

    }

    private static String parseDataP2(String s, int nodeIndex, int parentIndex) {
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

        // add node to list:
        MyNode parentToAdd;
        if (nodeIndex == 0) {
            parentToAdd = null; // this is the root
        } else {
            parentToAdd = nodes.get(parentIndex);
        }
        nodes.add(nodeIndex, new MyNode(nodeIndex, parentToAdd, quantityOfChildNodes, quantityOfMetadataEntries));

        // read child nodes
        for (int n = 0; n < quantityOfChildNodes; n++) {
            s = parseDataP2(s, n + 1, nodeIndex);
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

            nodes.get(nodeIndex).getMetadataEntries()[m] = metadataEntry;

        }

        return s;

    }

    @Getter
    @Setter
    private static class MyNode {

        int id = -1;
        MyNode parent;
//        List<MyNode> childNodes;
        int[] metadataEntries;

        MyNode(int id, MyNode parentInput, int numberOfChildren, int numberOfMetadataEntries) {
            this.id = id;

            if (parentInput != null) {
                this.parent = parentInput;
            }

//            this.childNodes = new ArrayList<>(numberOfChildren);

            this.metadataEntries = new int[numberOfMetadataEntries];
        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", parent=" + parent +
//                    ", \n childNodes=" + childNodes +
                    ", metadataEntries=" + Arrays.toString(metadataEntries) +
                    "}\n";
        }
    }
}
