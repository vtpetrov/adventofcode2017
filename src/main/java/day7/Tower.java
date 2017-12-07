package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tower {

    private String name;
    private Integer weight;
    private List<String> holdingTowers;

    public Tower() {

    }

    /**
     * Input might have the following format:
     * <pre>
     * Tower NOT holding another towers:     ktlj (57)
     * Tower holding another towers:         fwft (72) -> ktlj, cntj, xhth
     * </pre>
     *
     * @param inputFromFile
     */
    public Tower(String inputFromFile) {

        // name of tower is starting at the beginning until the first space
        this.name = inputFromFile.substring(0, inputFromFile.indexOf(" "));

        this.weight = Integer.valueOf(inputFromFile.substring(inputFromFile.indexOf("(") + 1, inputFromFile.indexOf(")")));

        if (inputFromFile.contains("->")) {
            // holding towers:
            this.holdingTowers = new ArrayList<>(Arrays.asList(inputFromFile.substring(inputFromFile.indexOf(">") + 2).split
                    (", ")));

        }

    }

    public static Tower fromStringName(String name) {
        Tower toReturn = new Tower();
        toReturn.setName(name);
        return toReturn;
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

    public List<String> getHoldingTowers() {
        return holdingTowers;
    }

    public void setHoldingTowers(List<String> holdingTowers) {
        this.holdingTowers = holdingTowers;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\nTower{");
        sb.append("name='").append(name).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", holdingTowers=").append(holdingTowers);
        sb.append('}');
        return sb.toString();
    }
}
