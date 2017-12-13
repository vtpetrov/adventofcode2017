package common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {


    private static final int TO_STRING_DAY = 12;
    public Boolean communicatesWithZero = null;
    private String name;
    private Integer weight;
    private List<String> holdingTowersAsStringNames;
    public Boolean traversed = false;
    private Long stackWeight = 0L;
    private Long totalWeight = 0L; // weight + stackWeight
    private int id;
    private List<Program> holdingPrograms;
    private ArrayList<Integer> communicatesWithIDs = new ArrayList<>();


    public Program() {

    }

    /**
     * Input might have the following format:
     * <pre>
     * Program NOT holding another programs:     ktlj (57)
     * Program holding another programs:         fwft (72) -> ktlj, cntj, xhth
     * </pre>
     *
     * @param inputFromFile
     */
    public Program(String inputFromFile) {

        // name OR id of tower is starting at the beginning until the first space
        String firstToken = inputFromFile.substring(0, inputFromFile.indexOf(" "));
        try {
            this.id = NumberFormat.getInstance().parse(firstToken).intValue();
        } catch (ParseException e) {
            this.name = String.valueOf(firstToken);
        }

        // set weight if available
        if (inputFromFile.contains("(")) {
            this.weight = Integer.valueOf(inputFromFile.substring(inputFromFile.indexOf("(") + 1, inputFromFile.indexOf(")")));
        }

        // set programs it communicates to (day12: <->) OR holding programs (day7: ->)
        if (inputFromFile.contains("<->")) {

            for (String elem : inputFromFile.substring(inputFromFile.indexOf(">") + 2).split(", ")) {
                this.communicatesWithIDs.add(Integer.valueOf(elem));
                if (this.getId() == 0 || Integer.valueOf(elem) == 0) {
                    this.communicatesWithZero = true;
                    this.traversed = true;
                    System.out.println("SETTING 'communicatesWithZero to TRUE-> " + this.getId() + ", " + elem);
                }
            }
        } else if (inputFromFile.contains("->")) {
            // holding programs:
            this.holdingTowersAsStringNames = new ArrayList<>(Arrays.asList(inputFromFile.substring(inputFromFile.indexOf(">") + 2).split
                    (", ")));

        }

    }

//    0 <-> 2
//    1 <-> 1
//    2 <-> 0, 3, 4
//    3 <-> 2, 4
//    4 <-> 2, 3, 6
//    5 <-> 6
//    6 <-> 4, 5
//
//    dihjv (2158) -> gausx, ncdmp, hozgrub
//    qhvca (428) -> odttvb, ymehff, ymyzbqc, jtdtmsi, wciuyuh
//    kuvqhnm (77)
//    eauol (56)
//    nwtkz (304) -> eykks, rxivjye


    public static Program fromStringName(String name) {
        Program toReturn = new Program();
        toReturn.setName(name);
        return toReturn;
    }

    public static int getToStringDay() {
        return TO_STRING_DAY;
    }

    public ArrayList<Integer> getCommunicatesWithIDs() {
        return communicatesWithIDs;
    }

    public void setCommunicatesWithIDs(ArrayList<Integer> communicatesWithIDs) {
        this.communicatesWithIDs = communicatesWithIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<String> getHoldingProgramsAsStringNames() {
        return holdingTowersAsStringNames;
    }

    public void setHoldingTowersAsStringNames(List<String> holdingTowersAsStringNames) {
        this.holdingTowersAsStringNames = holdingTowersAsStringNames;
    }

    public Long getStackWeight() {
        return stackWeight;
    }

    public void setStackWeight(Long stackWeight) {
        this.stackWeight = stackWeight;
    }

    public List<Program> getHoldingPrograms() {
        return holdingPrograms;
    }

    public void setHoldingPrograms(List<Program> holdingPrograms) {
        this.holdingPrograms = holdingPrograms;
    }

    public Long getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Long totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void addHoldingProgram(Program programToAdd) {
        if (this.holdingPrograms == null) {
            this.holdingPrograms = new ArrayList<>();
            holdingPrograms.add(programToAdd);
        } else {
            holdingPrograms.add(programToAdd);
        }
    }

    @Deprecated
    public void addCommunicatingProgram(Program programToAdd) {
//        System.out.println("DEBUG addCommunicatingProgram:      ADDING new programm to the list of communicating programs: id= " + programToAdd.getId());
//        if (this.communicatesWith == null) {
//            System.out.println("DEBUG   : creating new 'communicatesWith' ArrayList instance");
//            this.communicatesWith = new ArrayList<>();
//            System.out.println("(addCommunicatingProgram)_ communicatesWith  BEFORE ADD 1= " + communicatesWith.stream().map(Program::getId).collect
//                    (Collectors.toList()));
//            communicatesWith.add(programToAdd);
//            System.out.println("(addCommunicatingProgram)_communicatesWith  AFTER ADD 1= " + communicatesWith.stream().map(Program::getId).collect
//                    (Collectors.toList()));
//        } else {
//            System.out.println("(addCommunicatingProgram)_communicatesWith  BEFORE ADD 2= " + communicatesWith.stream().map(Program::getId).collect
//                    (Collectors.toList()));
//            communicatesWith.add(programToAdd);
//            System.out.println("(addCommunicatingProgram)_communicatesWith  AFTER ADD 2= " + communicatesWith.stream().map(Program::getId).collect
//                    (Collectors.toList()));
//        }
    }

    public void calculateDeiscWeight() {

        Long sumDiscWeights = 0L;
        Long sumDiscStackWeights = 0L;

        if (this.getHoldingPrograms() != null) {
            for (Program t : this.getHoldingPrograms()) {
                sumDiscWeights += t.getWeight();
                sumDiscStackWeights += t.getStackWeight();
            }
        }

    }

//    @Override
//    public String toString() {
//        return "Program{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", weight=" + weight +
//                ", holdingTowersAsStringNames=" + holdingTowersAsStringNames +
//                ", holdingPrograms=" + holdingPrograms +
//                ", stackWeight=" + stackWeight +
//                ", totalWeight=" + totalWeight +
//                ", communicatesWithIDs=" + communicatesWithIDs +
//                ", communicatesWith=" + communicatesWith +
//                "}\n";
//    }

    public String toString() {
        switch (TO_STRING_DAY) {
            case 12:
                return "Program{" +
                        "id=" + id +
                        ", communicatesWithIDs=" + communicatesWithIDs +
                        ", traversed=" + traversed +
                        ", communicatesWithZero=" + communicatesWithZero +
                        "}\n";
            default:
                return "Program{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", weight=" + weight +
                        ", holdingTowersAsStringNames=" + holdingTowersAsStringNames +
                        ", holdingPrograms=" + holdingPrograms +
                        ", stackWeight=" + stackWeight +
                        ", totalWeight=" + totalWeight +
                        ", communicatesWithIDs=" + communicatesWithIDs +
                        "}\n";
        }
    }

}
