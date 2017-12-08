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

    public static void calculateStackWeight(Tower towerInput) {

        System.out.println();
        System.out.print(towerInput.getName() + " | ");
        if (towerInput.getStackWeight() == 0L) {

            Long stackWeight = 0L;

            if (null == towerInput.getHoldingTowers()) { // if program doesn't hold other programs it's stack weight is 0:
                towerInput.setStackWeight(0L);
                System.out.print(towerInput.getWeight() + " || ");
                //                stackWeight += new Long(current.getWeight());
            } else {
                System.out.print(towerInput.getHoldingTowers().stream().map(Tower::getName).collect(Collectors.toList()) + " " +
                        "|||" +
                        " ");
                for (Tower t : towerInput.getHoldingTowers()) {
                    calculateStackWeight(t);
                    stackWeight += t.getWeight() + t.getStackWeight();
                }
                towerInput.setStackWeight(stackWeight);

            }

            System.out.println("[" + towerInput.getName() + "] stackWeight = " + stackWeight);

        }
    }

    public List<Tower> getTowersHolding(boolean holding) {

        List<Tower> towerList = new ArrayList<>();

        for (Tower entry : towers) {
            boolean isHolding = entry.getHoldingTowersAsStringNames() != null;

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
                if (checkHolding.getHoldingTowersAsStringNames() != null) {
                    if (checkHolding.getHoldingTowersAsStringNames().contains(checkSelf.getName())) {

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

    public void populateHoldedTowers() {
        for (Tower current : this.towers) {
            if (current.getHoldingTowersAsStringNames() != null) {
                for (String holdedTowerName : current.getHoldingTowersAsStringNames()) {
                    current.addHoldingTower(this.getTower(holdedTowerName));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Towers{" +
                "towers=" + towers +
                '}';
    }
}
