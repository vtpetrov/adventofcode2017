package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tower {

    private String name;
    private Integer weight;
    private List<String> holdingTowersAsStringNames;
    private List<Tower> holdingTowers;
    private Long stackWeight = 0L;

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
            this.holdingTowersAsStringNames = new ArrayList<>(Arrays.asList(inputFromFile.substring(inputFromFile.indexOf(">") + 2).split
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

    public List<String> getHoldingTowersAsStringNames() {
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

    public List<Tower> getHoldingTowers() {
        return holdingTowers;
    }

    public void setHoldingTowers(List<Tower> holdingTowers) {
        this.holdingTowers = holdingTowers;
    }

    public void addHoldingTower(Tower towerToAdd) {
        if (this.holdingTowers == null) {
            this.holdingTowers = new ArrayList<>();
            holdingTowers.add(towerToAdd);
        } else {
            holdingTowers.add(towerToAdd);
        }
    }

    public void calculateDeiscWeight() {

        Long sumDiscWeights = 0L;
        Long sumDiscStackWeights = 0L;

        if (this.getHoldingTowers() != null) {
            for (Tower t : this.getHoldingTowers()) {
                sumDiscWeights += t.getWeight();
                sumDiscStackWeights += t.getStackWeight();
            }
        }

    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tower{");
        sb.append("name='").append(name).append('\'');
        sb.append(", weight=").append(weight);
        sb.append(", holdingTowersAsStringNames=").append(holdingTowersAsStringNames);
        sb.append(", holdingTowers=").append(holdingTowers);
        sb.append(", stackWeight=").append(stackWeight);
        sb.append("}\n");
        return sb.toString();
    }
}
