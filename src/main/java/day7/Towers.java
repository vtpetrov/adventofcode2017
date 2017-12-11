package day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Towers {

    List<Tower> towers = new ArrayList<>();
    public static Tower imbalancedHolder;
    public static List<Tower> imbalancedPrograms;
    public static Tower wrongProgram;
    public static Tower refProgram;
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
        if (towerInput.getStackWeight() == 0L) {

            Long stackWeight = 0L;

            if (null == towerInput.getHoldingTowers()) { // if program doesn't hold other programs it's stack weight is 0:
                towerInput.setStackWeight(0L);
            } else {
                for (Tower t : towerInput.getHoldingTowers()) {
                    calculateStackWeight(t);
                    stackWeight += t.getWeight() + t.getStackWeight();
                }
                towerInput.setStackWeight(stackWeight);

            }

            System.out.println("[" + towerInput.getName() + "] stackWeight = " + stackWeight);
            towerInput.setTotalWeight(towerInput.getWeight() + towerInput.getStackWeight());

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

        System.out.println("\n - Balancing programs.... ");
        System.out.println("    [" + imbalancedPrograms.stream().map(Tower::getName).collect(Collectors.toList()) + "]");
        System.out.println("    [" + imbalancedPrograms.stream().map(Tower::getWeight).collect(Collectors.toList()) + "]");
        System.out.println("    [" + imbalancedPrograms.stream().map(Tower::getStackWeight).collect(Collectors.toList()) + "]");

        Map<Long, Integer> weightCounts = new HashMap<>();

        for (int i = 0; i < imbalancedPrograms.size(); i++) {
            Tower curr = imbalancedPrograms.get(i);
            long currTotal = curr.getWeight() + curr.getStackWeight();
            System.out.println(curr.getName() + ": " + "weight= '" + curr.getWeight() + "', stackWeight= '" + curr
                    .getStackWeight() + "' ; TOTAL: " + currTotal);
            if (weightCounts.containsKey(currTotal)) { // increase occurrences by 1:
                weightCounts.put(currTotal, weightCounts.get(currTotal) + 1);
            } else {
                weightCounts.put(currTotal, 1);
            }
        }

        for (Map.Entry<Long, Integer> test : weightCounts.entrySet()) {
            if (test.getValue().equals(1)) {
                System.out.println(" [!] imbalanced weight : " + test.getKey());
                for (int j = 0; j < imbalancedPrograms.size(); j++) {
                    Tower obj = imbalancedPrograms.get(j);
                    if (obj.getTotalWeight().equals(test.getKey())) {
                        wrongProgram = obj;
                        refProgram = imbalancedPrograms.get(j + 1 % imbalancedPrograms.size());

                        System.out.println(String.format("Wrong program: name=%s, weight=%s, total=%s", wrongProgram.getName(),
                                wrongProgram.getWeight(), wrongProgram.getTotalWeight()));
                        System.out.println(String.format("REF program: name=%s, weight=%s, total=%s", refProgram.getName(),
                                refProgram.getWeight(), refProgram.getTotalWeight()));

                    }
                }
                break;
            }
        }

        long diff = wrongProgram.getTotalWeight() - refProgram.getTotalWeight();
        System.out.println("diff = " + diff);

        // balance the wrong program:
        System.out.println("OLD program weight = " + wrongProgram.getWeight());
        long correctWeight = wrongProgram.getWeight() - diff;
        System.out.println("NEW program weight = [" + correctWeight + "]");


        return 0;
    }

    public void findInbalancedDisks() {

        totalka:
        for (Tower current : this.getTowers()) {
            System.out.println("\n totalka: " + current.getName());

            long[] weights;
            if (current.getHoldingTowers() != null) {

                weights = new long[current.getHoldingTowers().size()];
                int i = 0;
                for (Tower h : current.getHoldingTowers()) {
                    weights[i] = h.getWeight() + h.getStackWeight();
                    i++;
                }

                long curr;
                for (int j = 0; j < weights.length - 1; j++) {
                    curr = weights[j];

                    if (weights[j + 1] != curr) {
                        imbalancedHolder = current;
                        imbalancedPrograms = current.getHoldingTowers();
                        break totalka;
                    }
                }

            }

        }


    }
}
