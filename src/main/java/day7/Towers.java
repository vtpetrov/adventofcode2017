package day7;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Towers {

    List<Tower> towers = new ArrayList<>();

    public Towers() {
    }

    public Towers(List<Tower> input) {
        this.towers = input;
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public void setTowers(List<Tower> towers) {
        this.towers = towers;
    }

    public void addTower(Tower t) {
        towers.add(t);
    }

    public Tower getTower(String name) {

        for (Tower t : towers) {
            if (t.getName().equals(name)) {
                return towers.get(towers.indexOf(t));
            }
        }
        return null;
    }

    public List<String> getNames() {

        return towers.stream().map(Tower::getName).collect(Collectors.toList());
    }

    public List<Integer> getWeights() {

        return towers.stream().map(Tower::getWeight).collect(Collectors.toList());
    }

    public Boolean isHold(String name) {

        return null;
    }

    public List<Tower> getTowersHolding(boolean holding) {

        List<Tower> towerList = new ArrayList<>();

        for (Tower entry : towers) {
            boolean isHolding = entry.getHoldingTowers() != null;

            if (isHolding == holding) {
                towerList.add(entry);
            }
        }

        return towerList;
    }

    public List<Tower> getTowersBeingHold(boolean beingHold) {

        List<Tower> listToReturn = new ArrayList<>();

        for (Tower checkSelf : this.towers) {

            boolean isBenigHold = false;

            for (Tower checkHolding : this.towers) {
                if (checkHolding.getHoldingTowers() != null) {
                    if (checkHolding.getHoldingTowers().contains(checkSelf.getName())) {

                        isBenigHold = true;

                    }
                }
            }

            if (beingHold == isBenigHold) { // result match what we want - holded OR not holded

                listToReturn.add(checkSelf);
            }


        }

//        System.out.println("FIN:  listToReturn = " + listToReturn.stream().map(Tower::getName).collect(Collectors.toList()));

        return listToReturn;
    }


    @Override
    public String toString() {
        return "Towers{" +
                "towers=" + towers +
                '}';
    }
}
