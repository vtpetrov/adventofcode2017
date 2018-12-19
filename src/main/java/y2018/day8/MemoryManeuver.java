package y2018.day8;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static helper.InputLoader.*;

public class MemoryManeuver {

    //    private static final String INPUT_FILE_NAME = "year_2018/day8_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";
    private static int sumOfMetadataEntries;
    private static int sumOfNodeValues;
    private static List<MyNode> nodes;
    private static String s;
    private static int parentNodeIndex;
    private static int recursionCalls = 0;


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

        s = getMainIn().nextLine();
        solutionP1();


        System.out.println("\n    Part 1 solutionP1:   the sum of all metadata entries= " + sumOfMetadataEntries);

    }

    private static void solutionP1() {

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
            solutionP1();
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

    }

    private static void partTwo() {

        s = getMainIn().nextLine();
        solutionP2();

        System.out.println("\n    Part 2 solutionP1:   YYYYYYYYYYYY= [");
    }

    private static void solutionP2() {

        nodes = new ArrayList<>();

        parseDataP2(-1);

        System.out.println("END nodes = \n" + nodes);

    }

    private static void parseDataP2(int parentIndex) {

        recursionCalls++;
        System.out.println("recursionCalls = " + recursionCalls);

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
        if (nodes.size() == 0) {
            parentToAdd = null; // this is the root
        } else {
            parentToAdd = nodes.get(parentIndex);
        }

        nodes.add(new MyNode(parentToAdd, quantityOfChildNodes, quantityOfMetadataEntries));
        int indexOfLastAdded = nodes.size() - 1;
        nodes.get(indexOfLastAdded).setId(indexOfLastAdded);

        // read child nodes
        for (int n = 0; n < quantityOfChildNodes; n++) {
            parseDataP2(indexOfLastAdded);

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

            nodes.get(indexOfLastAdded).getMetadataEntries()[m] = metadataEntry;

        }

    }

    @Getter
    @Setter
    private static class MyNode {

        int id = -1;
        MyNode parent;
        List<MyNode> childNodes;
        int[] metadataEntries;

        MyNode(MyNode parentInput, int numberOfChildren, int numberOfMetadataEntries) {

            if (parentInput != null) {
                this.parent = parentInput;
            }

//            this.childNodes = new ArrayList<>(numberOfChildren);

            this.metadataEntries = new int[numberOfMetadataEntries];
        }

        /**
         * traverse the ready structure and populate children fields
         */
        void populateChildren(){

            for(MyNode currentNode : nodes){

            }

        }

        @Override
        public String toString() {
            return "MyNode{" +
                    "id=" + id +
                    ", parent=" + (parent == null ? parent : parent.getId()) +
                    ", childs=" + (childNodes == null ? childNodes : childNodes.stream().map(MyNode::getId).collect(Collectors.toList())) +
                    ", metadataEntries=" + Arrays.toString(metadataEntries) +
                    "};\n";
        }
    }
}
