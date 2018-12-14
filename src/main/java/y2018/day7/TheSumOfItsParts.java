package y2018.day7;

import java.util.*;

import static helper.InputLoader.*;

public class TheSumOfItsParts {

        private static final String INPUT_FILE_NAME = "year_2018/day7_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";
    private static Map<String, Step> allSteps = new TreeMap<>();
    private static PriorityQueue availabeSteps = new PriorityQueue();
    private static StringBuilder orderOfSteps = new StringBuilder();


    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2018    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 7 ===---     ");
        System.out.println("              - The Sum of Its Parts -     ");

        System.out.println("\n    ---=== Part 1 ===---     ");

        loadInput(INPUT_FILE_NAME, "");

        // Loop through instructions and mark steps as being AVAILABLE + add them to allSteps list:
        while (getMainIn().hasNextLine()) {
            String line = getMainIn().nextLine();
            Step inputPrereq, inputDep;
//            Step C must be finished before step A can begin.
//            Step C must be finished before step F can begin.
//            Step A must be finished before step B can begin.
//            Step A must be finished before step D can begin.
//            Step B must be finished before step E can begin.
//            Step D must be finished before step E can begin.
//            Step F must be finished before step E can begin.

            inputPrereq = new Step(line.substring(5, 6));
            inputDep = new Step(line.substring(36, 37));

            inputPrereq.addDependent(inputDep);
            inputDep.addPrerequisite(inputPrereq);

            // add step 1 to list
            if (allSteps.containsKey(inputPrereq.name)) {
                Step existingPrereq = allSteps.get(inputPrereq.name);
                existingPrereq.addDependent(inputDep);

                allSteps.replace(inputPrereq.name, existingPrereq);

            } else {
                allSteps.put(inputPrereq.name, inputPrereq);
            }

            // add step 2 to list
            if (allSteps.containsKey(inputDep.name)) {
                Step existingDependent = allSteps.get(inputDep.name);
                existingDependent.addPrerequisite(inputPrereq);

                allSteps.replace(inputDep.name, existingDependent);

            } else {
                allSteps.put(inputDep.name, inputDep);
            }


        }

        for (Step s : allSteps.values()) {
            System.out.println("s = " + s);
        }


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

        int loop = 1;
        while (allSteps.size() > 0){
            System.out.println("loop = " + loop);
            recalculateAvailableSteps();
            execute();
            loop++;

            System.out.println("    in p1 ...");
            for (Step s : allSteps.values()) {
                System.out.println("        s = " + s);
            }

        }

        System.out.println("\n    Part 1 solution:   Steps are completed in the following order= " + orderOfSteps);

    }

    /**
     * - removes 1st step from availabeSteps queue
     * - adds it to the orderOfSteps string
     * - remove executed from prereqs
     */
    private static void execute() {

        Step executed = (Step) availabeSteps.poll();

        orderOfSteps.append(executed.getName());

        // update prereqs - remove executed step from prereqs of other steps
        for (Step s : allSteps.values()) {
            s.getPrereqs().remove(executed);
        }

        // remove executed step from the list:
        allSteps.remove(executed.getName());

        System.out.println("executed = " + executed);

    }

    /**
     * mark available steps:
     */
    private static void recalculateAvailableSteps() {
        // add to availabeSteps queue
        for (Step curr : allSteps.values()) {
            if (curr.getPrereqs().size() == 0) {// if no prereqs are present, this step is available for execution
                //add the step to the ordered queue if it is not there:
                if(!availabeSteps.contains(curr)) availabeSteps.add(curr);
            }
        }
    }

    private static void partTwo() {


        System.out.println("\n    Part 2 solution:   YYYYYYYYYYYY= [");
    }

}
