package day8;

import lombok.Data;
import lombok.ToString;

import java.util.*;

import static helper.InputLoader.*;

public class ILikeRegisters {

    private static final String INPUT_FILE_NAME = "day8_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";

    private static Set<String> registerNames = new TreeSet<>();
    private static List<Instruction> instructions = new ArrayList<>();
    private static Map<String, Register> registers = new TreeMap<>();
    private static long MAX_VALUE;

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
     * Each instruction consists of several parts: the register to modify, whether to increase or decrease that register's value, the amount by which to increase or decrease it, and a condition. If the condition fails, skip the instruction without modifying the register. The registers all start at 0. The instructions look like this:
     * <p>
     * b inc 5 if a > 1
     * a inc 1 if b < 5
     * c dec -10 if a >= 1
     * c inc -20 if c == 10
     * These instructions would be processed as follows:
     * <p>
     * Because a starts at 0, it is not greater than 1, and so b is not modified.
     * a is increased by 1 (to 1) because b is less than 5 (it is 0).
     * c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
     * c is increased by -20 (to -10) because c is equal to 10.
     * After this process, the largest value in any register is 1.
     * <p>
     * You might also encounter <= (less than or equal to) or != (not equal to). However, the CPU doesn't have the bandwidth to tell you what all the registers are named, and leaves that to you to determine.
     * <p>
     * What is the largest value in any register after completing the instructions in your puzzle input?
     */
    private static void partOne() {

        loadInput(INPUT_FILE_NAME);

        while (getMainIn().hasNextLine()) {
            String nextLine = getMainIn().nextLine();

            registerNames.add(nextLine.substring(0, nextLine.indexOf(" ")));
            instructions.add(new Instruction(nextLine));

        }

        // initialize Registers states - add name and initial value '0':
        for (String name : registerNames) {
            registers.put(name, new Register(name));
        }

        System.out.println("        ----    BEFORE  -   -   -   -   -");
        System.out.println("registers = [" + registers.size() + "], " + registers);
        System.out.println("instructions = [" + instructions.size() + "], " + instructions);

        for (Instruction instruction : instructions) {
            instruction.apply();
        }

        System.out.println("    ----    AFTER   -  -    -       -   -   -   -");
        System.out.println("registers = " + registers);

        findLargestValueAmongRegisters();

        System.out.println("\nPart 1 solution:");
        System.out.println("    MAX_VALUE = " + MAX_VALUE);

        closeInput();
    }

    private static void findLargestValueAmongRegisters() {

        MAX_VALUE = registers.get(new ArrayList(registerNames).get(0)).getValue();

        for (String register : registerNames) {
            long currentValue = registers.get(register).getValue();
            if (currentValue > MAX_VALUE) {
                MAX_VALUE = currentValue;
            }
        }
    }

    private static void partTwo() {
        loadInput(INPUT_FILE_NAME);

        while (getMainIn().hasNextLine()) {
            System.out.println("part 2 empty...");
        }

        System.out.println("Part 2 solution:");

        closeInput();


    }


    public enum Operation {

        INCREASE,
        DECREASE;

        static Operation fromString(String input) {
            switch (input) {
                case "inc":
                    return INCREASE;
                case "dec":
                    return DECREASE;
                default:
                    throw new Error("VTP - unknown operation:" + input);
            }
        }
    }

    private enum Operator {
        GT,
        LT,
        GTOET,
        LTOET,
        EQUAL_TO,
        NOT_EQUAL_TO;

        static Operator fromString(String input) {
            switch (input) {
                case ">":
                    return GT;
                case "<":
                    return LT;
                case ">=":
                    return GTOET;
                case "<=":
                    return LTOET;
                case "==":
                    return EQUAL_TO;
                case "!=":
                    return NOT_EQUAL_TO;
                default:
                    throw new Error("VTP - unknown operator:" + input);
            }
        }
    }

    @Data
    private static class Condition {

        String dependsOnRegister;
        Operator operator;
        long value;

        Condition(String dependsOnRegister, String operator, String value) {
            this.dependsOnRegister = dependsOnRegister;
            this.operator = Operator.fromString(operator);
            this.value = Integer.valueOf(value);
        }

        public boolean check() {
            switch (this.operator) {

                case GT:
                    return (registers.get(this.dependsOnRegister).getValue() > this.value);
                case LT:
                    return (registers.get(this.dependsOnRegister).getValue() < this.value);
                case GTOET:
                    return (registers.get(this.dependsOnRegister).getValue() >= this.value);
                case LTOET:
                    return (registers.get(this.dependsOnRegister).getValue() <= this.value);
                case EQUAL_TO:
                    return (registers.get(this.dependsOnRegister).getValue() == this.value);
                case NOT_EQUAL_TO:
                    return (registers.get(this.dependsOnRegister).getValue() != this.value);
                default:
                    return false;
            }
        }
    }

    @Data
    @ToString(includeFieldNames = false)
    private static class Instruction {

        String affectsRegister;
        Operation operation;
        Integer amount;
        Condition condition;

        Instruction(String input) {
//            b inc 5 if a > 1
            //            split into 7 tokens :
//            0. affects register
//            1. operation
//            2. amount
//            3. if - transparent, just skip it
//            4, 5, 6 - Condition: conditionalRegister, operator, value

            String[] tokens = input.split(" ");

            this.affectsRegister = tokens[0];
            this.operation = Operation.fromString(tokens[1]);
            this.amount = Integer.valueOf(tokens[2]);
            this.condition = new Condition(tokens[4], tokens[5], tokens[6]);
        }


        boolean apply() {

            if (this.condition.check()) {
                switch (operation) {

                    case INCREASE:
                        registers.get(this.affectsRegister).value += this.amount;
                        return true;
                    case DECREASE:
                        registers.get(this.affectsRegister).value -= this.amount;
                        return true;
                }
            }

            return false;
        }
    }

    @Data
    @ToString(includeFieldNames = false)
    private static class Register {

        private String name;
        private long value;

        Register(String nameParam) {
            this.name = nameParam;
        }
    }
}
