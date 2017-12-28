package day18;

import lombok.Data;
import lombok.ToString;

import java.util.*;

import static helper.InputLoader.closeInput;
import static helper.InputLoader.getMainIn;
import static helper.InputLoader.loadInput;

public class Duet {

    private static final String INPUT_FILE_NAME = "day18_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";

    private static List<Instruction> instructions = new ArrayList<>();
    static Set<String> tempRegisters = new HashSet<>();
    private static Map<String, Long> registers = new TreeMap<>();
    private static Long lastPlayedSoundFrequency;
    private static boolean recovered = false;

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 18 ===---     ");
        System.out.println("                   - Duet -     ");
        System.out.println("\n              ---=== Part 1 ===---     ");

        loadInput(INPUT_FILE_NAME, "");
        partOne();

        System.out.println("\n  Part 1 solution:    \"the value of the recovered frequency\"= [" + lastPlayedSoundFrequency + "]");


        long p2Start = new Date().getTime();
        System.out.println("\n  P1 Duration: " + (p2Start - start) + "ms (" + (p2Start - start) / 1000 + "s)");

        System.out.println("\n              ---=== Part 2 ===---     ");

        partTwo();
        System.out.println("\n    Part 2 solution:      YYYYYYYYYYYY= [" + 2 + "]");

        closeInput();


        long end = new Date().getTime();
        System.out.println("\n  P2 Duration: " + (end - p2Start) + "ms (" + (end - p2Start) / 1000 + "s)");
        System.out.println("\nTotal Duration: " + (end - start) + "ms (" + (end - start) / 1000 + "s)");
        System.out.println("\n:::END = " + end);
    }

    /**
     * ou discover a tablet containing some strange assembly code labeled simply "Duet".
     * Rather than bother the sound card with it, you decide to run the code yourself.
     * Unfortunately, you don't see any documentation, so you're left to figure out what the instructions mean on your own.
     * <p>
     * It seems like the assembly is meant to operate on a set of registers that are each named with
     * a single letter and that can each hold a single integer. You suppose each register should start with a value of 0.
     * <p>
     * There aren't that many instructions, so it shouldn't be hard to figure out what they do. Here's what you determine:
     * <p>
     * snd X plays a sound with a frequency equal to the value of X.
     * set X Y sets register X to the value of Y.
     * add X Y increases register X by the value of Y.
     * mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
     * mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
     * rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
     * jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instructionType, an offset of -1 jumps to the previous instructionType, and so on.)
     * <p>
     * Many of the instructions can take either a register (a single letter) or a number.
     * The value of a register is the integer it contains; the value of a number is that number.
     * <p>
     * After each jump instructionType, the program continues with the instructionType to which the jump jumped.
     * After any other instructionType, the program continues with the next instructionType.
     * Continuing (or jumping) off either end of the program terminates it.
     * <p>
     * For example:
     * <p>
     * set a 1
     * add a 2
     * mul a a
     * mod a 5
     * snd a
     * set a 0
     * rcv a
     * jgz a -1
     * set a 1
     * jgz a -2
     * The first four instructions set a to 1, add 2 to it, square it, and then set it to itself modulo 5, resulting in a value of 4.
     * Then, a sound with frequency 4 (the value of a) is played.
     * After that, a is set to 0, causing the subsequent rcv and jgz instructions to both be skipped (rcv because a is 0, and jgz because a is not greater than 0).
     * Finally, a is set to 1, causing the next jgz instructionType to activate, jumping back two instructions to another jump, which jumps again to the rcv, which ultimately triggers the recover operation.
     * At the time the recover operation is executed, the frequency of the last sound played is 4.
     * <p>
     * What is the value of the recovered frequency (the value of the most recently played sound) the first time a rcv instructionType is executed with a non-zero value?
     */
    private static void partOne() {

        while (getMainIn().hasNextLine()) {

            instructions.add(new Instruction(getMainIn().nextLine()));
        }


        for (Instruction curr : instructions) {
            if (curr.getAffectedRegisterOrNumber() instanceof String) {
                tempRegisters.add((String) curr.getAffectedRegisterOrNumber());
            }
        }

        // initialize registers Map:
        for (String curr : tempRegisters) {
            registers.put(curr, 0L);
        }

        execute();


    }

    private static void partTwo() {

    }

    /**
     * set a 1
     * add a 2
     * mul a a
     * <p>
     * set X Y      sets register X to the value of Y.
     * add X Y      increases register X by the value of Y.
     * mul X Y      sets register X to the result of multiplying the value contained in register X by the value of Y.
     * mod X Y      sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
     * <p>
     * snd X        plays a sound with a frequency equal to the value of X.
     * rcv X        recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
     * jgz X Y      jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instructionType, an offset of -1 jumps to the previous instructionType, and so on.)
     * <p>
     * <p>
     * Many of the instructions can take either a register (a single letter) or a number.
     * The value of a register is the integer it contains; the value of a number is that number.
     */
    @Data
    public static class Instruction {

        InstructionType type;
        Object affectedRegisterOrNumber;
        Object value; // offset

        public Long getValue() {
            return value instanceof Integer ? Long.valueOf(String.valueOf(value)) : registers.get(String.valueOf(value));
        }

        public Instruction(String s) {
            String[] tokens = s.split(" ");

            this.type = InstructionType.fromString(tokens[0]);

            try {
                this.affectedRegisterOrNumber = Integer.valueOf(tokens[1]);
            } catch (NumberFormatException e) {
                this.affectedRegisterOrNumber = tokens[1];
            }

            if (tokens.length > 2) {
                try {
                    this.value = Integer.valueOf(tokens[2]);
                } catch (NumberFormatException e) {
                    this.value = tokens[2];
                }

            }


        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("{");
            sb.append("type=").append(type);
            sb.append(", ").append(affectedRegisterOrNumber);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }

    static void execute() {
        int cycles = 1;

        int i = 0;
        while (!recovered && i < instructions.size()) {
//            System.out.println("cycles = " + cycles);
            cycles++;
//            System.out.println("    i = " + i);


            switch (instructions.get(i).type) {

                case SET:
                    //                    set X Y      sets register X to the value of Y.
                    registers.put((String) instructions.get(i).getAffectedRegisterOrNumber(), Long.valueOf(instructions.get(i).getValue()));
                    i++;
                    break;
                case ADD:
                    //                    add X Y      increases register X by the value of Y.
                    registers.put((String) instructions.get(i).getAffectedRegisterOrNumber(), registers.get(instructions.get(i).getAffectedRegisterOrNumber()) + instructions.get(i).getValue());
                    i++;
                    break;
                case MUL:
                    //                    mul X Y      sets register X to the result of multiplying the value contained in register X by the value of Y.
                    registers.put((String) instructions.get(i).getAffectedRegisterOrNumber(), registers.get(instructions.get(i).getAffectedRegisterOrNumber()) * instructions.get(i).getValue());
                    i++;
                    break;
                case MOD:
                    //                    mod X Y      sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
                    registers.put((String) instructions.get(i).getAffectedRegisterOrNumber(), registers.get(instructions.get(i).getAffectedRegisterOrNumber()) % instructions.get(i).getValue());
                    i++;
                    break;
                case SND:
                    //                    snd X        plays a sound with a frequency equal to the value of X.
                    lastPlayedSoundFrequency = instructions.get(i).affectedRegisterOrNumber instanceof Integer ? (Long) instructions.get(i).affectedRegisterOrNumber :
                            registers.get(String.valueOf(instructions.get(i).affectedRegisterOrNumber));
//                    System.out.println("        lastPlayedSoundFrequency = " + lastPlayedSoundFrequency);
                    i++;
                    break;
                case RCV:
                    //                    rcv X        recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
                    recovered = instructions.get(i).affectedRegisterOrNumber instanceof Integer ? (Integer) instructions.get(i).affectedRegisterOrNumber != 0 : registers.get(String.valueOf(instructions.get(i).affectedRegisterOrNumber)) != 0;
                    //                        What is the value of the recovered frequency (the value of the most recently played sound) the first time a rcv instruction is executed with a non-zero value?
//                    System.out.println("        recovered = " + recovered);
                    i++;
                    break;
                case JGZ:
                    //                    jgz X Y      jumps with an offset of the value of Y, but only if the value of X is greater than zero.
                    //                      (An offset of 2 skips the next instructionType, an offset of -1 jumps to the previous instructionType, and so on.)
                    if (instructions.get(i).affectedRegisterOrNumber instanceof Integer ? (Integer) instructions.get(i).affectedRegisterOrNumber > 0 : registers.get(String.valueOf(instructions.get(i).affectedRegisterOrNumber)) > 0) {
//                        System.out.println(String.format("      JGZ: from '%s' with '%s' to '%s'", i, instructions.get(i).getValue(), i + instructions.get(i).getValue()));
                        i += instructions.get(i).getValue();
                    } else {
                        i++;
                    }
                    break;
            }
//            System.out.println("        registers = " + registers);
        }

    }

    @Data
    @ToString(includeFieldNames = false)
    class Register {
        String name;
        int value;

        public Register(String name) {
            this.name = name;
        }
    }

    enum InstructionType {
        SND,
        SET,
        ADD,
        MUL,
        MOD,
        RCV,
        JGZ;

        static InstructionType fromString(String input) {
            switch (input) {
                case "snd":
                    return SND;
                case "set":
                    return SET;
                case "add":
                    return ADD;
                case "mul":
                    return MUL;
                case "mod":
                    return MOD;
                case "rcv":
                    return RCV;
                case "jgz":
                    return JGZ;
                default:
                    throw new Error("ERROR VTP: invalid InstructionType!");
            }
        }
    }

}