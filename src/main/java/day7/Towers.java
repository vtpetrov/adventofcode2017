package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Towers {

    List<Tower> towers = new ArrayList<>();
    public static Tower imbalancedHolder;
    public static List<Tower> imbalancedPrograms;
    public static long[] imbalancedProgramWeights;
    static int count = 0;


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
        count++;
        System.out.println("\n--- count= " + count + "\n");
        System.out.print(towerInput.getName() + " 1| ");
        if (towerInput.getStackWeight() == 0L) {

            Long stackWeight = 0L;

            if (null == towerInput.getHoldingTowers()) { // if program doesn't hold other programs it's stack weight is 0:
                towerInput.setStackWeight(0L);
                System.out.print(towerInput.getWeight() + " 2|| ");
            } else {
                System.out.println("    - : " + towerInput + " Dyrji kuli, rekursiq");
                System.out.print(towerInput.getHoldingTowers().stream().map(Tower::getName).collect(Collectors.toList()) + " " +
                        "3|||" +
                        " ");
                for (Tower t : towerInput.getHoldingTowers()) {
                    calculateStackWeight(t);
                    stackWeight += t.getWeight() + t.getStackWeight();
                }
                towerInput.setStackWeight(stackWeight);

            }

            System.out.println("[" + towerInput.getName() + "] stackWeight = " + stackWeight);

        } else {
            System.out.println(" Veche e klkulirana... : " + towerInput.getStackWeight());
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

    public static int calcBalancingValue() {

        return 0;
    }

    public void findInbalancedDisks() {
        // holder
        // pgograms

        totalka:
        for (Tower current : this.getTowers()) {
            System.out.println("\n totalka: " + current.getName());

            long[] weights;
            if (current.getHoldingTowers() != null) {

                weights = new long[current.getHoldingTowers().size()];
                int i = 0;
                for (Tower h : current.getHoldingTowers()) {
                    System.out.println("        h.getName() = " + h.getName());
                    weights[i] = h.getWeight() + h.getStackWeight();

                    System.out.print("        h.getWeight(): '" + h.getWeight());
                    System.out.print("' + h.getStackWeight(): '" + h.getStackWeight());
                    System.out.println("' = weights[i]: '" + weights[i]);
                    System.out.println("'");
                    i++;
                }
                System.out.println("Arrays.toString(weights) = " + Arrays.toString(weights));

                long curr;
                for (int j = 0; j < weights.length - 1; j++) {
                    curr = weights[j];
                    System.out.println("j = " + j);
                    System.out.println("curr = " + curr);
                    if (weights[j + 1] != curr) {
                        System.out.println("weights[j + 1] = " + weights[j + 1]);

                        imbalancedHolder = current;
                        imbalancedPrograms = current.getHoldingTowers();
                        break totalka;
                    }
                }

            }

        }

    }
}
