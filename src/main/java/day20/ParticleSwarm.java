package day20;

import java.util.*;
import java.util.stream.Collectors;

import static helper.InputLoader.*;

public class ParticleSwarm {

    //    private static final String INPUT_FILE_NAME = "day20_input.txt";
    private static final String INPUT_FILE_NAME = "debug.txt";

    private static List<Particle> particlesInitial = new ArrayList<>();
    private static List<Particle> remainingParticles;
    private static int ticks;
    private static int collisions;
    private static Map<Point, Integer> collisionsMap = new HashMap<>();

    public static void main(String[] args) throws Throwable {
        System.out.println("----   ADVENT Of code   2017    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 20 ===---     ");
        System.out.println("                   - Particle Swarm -     ");
        System.out.println("\n    ---=== Part 1 ===---     ");

        String line;
        loadInput(INPUT_FILE_NAME, "");

        while (getMainIn().hasNextLine()) {
            line = getMainIn().nextLine();
            particlesInitial.add(Particle.parse(line));
        }

        long minAcc, minVel, minPos;

        List<Particle> minAccList = new ArrayList<>();
        List<Particle> minVelList = new ArrayList<>();
        List<Particle> minPosList = new ArrayList<>();


        // - acceleration:
        minAcc = Collections.min(particlesInitial.stream().map(Particle::getAcceleration).map(Point::getDistanceToCenter).collect
                (Collectors
                        .toSet
                                ()));
        particlesInitial.stream().forEach(entry -> {
            if (entry.getAcceleration().getDistanceToCenter() == minAcc) minAccList.add(entry);
        });

        // - velocity:
        minVel = Collections.min(minAccList.stream().map(Particle::getVelocity).map(Point::getDistanceToCenter).collect
                (Collectors
                        .toSet
                                ()));
        minAccList.stream().forEach(entry -> {
            if (entry.getVelocity().getDistanceToCenter() == minVel) minVelList.add(entry);
        });

        // - position:
        minPos = Collections.min(minVelList.stream().map(Particle::getPosition).map(Point::getDistanceToCenter).collect
                (Collectors
                        .toSet
                                ()));
        minVelList.stream().forEach(entry -> {
            if (entry.getPosition().getDistanceToCenter() == minPos) minPosList.add(entry);
        });


        System.out.println("minAcc = " + minAcc);
        System.out.println("minVel = " + minVel);
        System.out.println("minPos = " + minPos);

        System.out.println("minPosList = " + minPosList);
        System.out.println("minPosList.size() = " + minPosList.size());

        if (minPosList.size() != 1) {
            System.out.println("WARNING more than 1 point satisfies condition!!!");
        } else {
            System.out.println("\n    Part 1 solution:   closest particle index = [" + particlesInitial.indexOf(minPosList
                    .get(0)
            ) + "]");
        }

        long p2Start = new Date().getTime();
        System.out.println("\nP1 Duration: " + (p2Start - start) + "ms (" + (p2Start - start) / 1000 + "s)");


        remainingParticles = new ArrayList<>(particlesInitial);
        int c = 1;
        while (c < 50 && remainingParticles.size() > 1) {
            removeCollisions();
            tick(remainingParticles);
            c++;
            System.out.println("         remaining particles= [" + remainingParticles.size() + "] " + remainingParticles);
        }

        System.out.println("    Part 2 solution:   remaining particles= [" + remainingParticles.size() + "] " + remainingParticles);

        System.out.println("\n IMPl2");
        remainingParticles = new ArrayList<>(particlesInitial);
        c = 1;
        while (c < 2 && remainingParticles.size() > 1) {
            impl2();
            tick(remainingParticles);
            c++;
            System.out.println("       IMPl2  remaining particles= [" + remainingParticles.size() + "] " + remainingParticles);
        }
        closeInput();


        long end = new Date().getTime();
        System.out.println("\nP2 Duration: " + (end - p2Start) + "ms (" + (end - p2Start) / 1000 + "s)");
        System.out.println("\nTotal Duration: " + (end - start) + "ms (" + (end - start) / 1000 + "s)");

        System.out.println("\n:::END = " + end);

    }

    private static void impl2() {

        System.out.println("IMPL 2 getPositionSimpleValue = " + remainingParticles.get(0).getPositionSimpleValue());

        for (Point entry : remainingParticles.stream().map(Particle::getPosition).collect(Collectors.toList())) {
            collisionsMap.merge(entry, 1, Integer::sum);
        }

        if (Collections.max(collisionsMap.values()) > 1) {
            System.out.println("there are points to remove");
            System.out.println("collisionsMap = " + collisionsMap);
        }
    }

//    private static List<Particle> particlesInitial = new ArrayList<>();
//    private static List<Particle> remainingParticles = new ArrayList<>();

    private static void removeCollisions() {
        collisions++;
        System.out.println("\nremoving collisions... " + collisions);

        List<Particle> particlesToRemove = new ArrayList<>();

        for (int i = 0; i < remainingParticles.size(); i++) {
            boolean match = false;

            for (int j = i + 1; j < remainingParticles.size(); j++) {

//                System.out.println("        remainingParticles.get(i).getPosition() = " + remainingParticles.get(i).getPosition());
//                System.out.println("        remainingParticles.get(j).getPosition() = " + remainingParticles.get(j).getPosition());
//                System.out.println("        ... i equals j= " + remainingParticles.get(i).getPosition().equals(remainingParticles
//                        .get(j)
//                        .getPosition()));

                if (remainingParticles.get(i).getPosition().equals(remainingParticles.get(j).getPosition())) {
                    System.out.println(" !!    MATCH       !!");
                    particlesToRemove.add(remainingParticles.get(j));
                    System.out.println("        add j to remove - " + remainingParticles.get(j));
                    match = true;
                }
            }

            if (match) {
                particlesToRemove.add(remainingParticles.get(i));
                System.out.println("        add i to remove - " + remainingParticles.get(i));

                remainingParticles.removeAll(particlesToRemove);
                System.out.println("    - removed collisions:       [" + particlesToRemove.size() + "]");
            }


//            System.out.println("\n   F Lists:    particlesToRemove  = " + particlesToRemove);
//            System.out.println("   F Lists:    remainingParticles = " + remainingParticles);

        }
    }

    private static void tick(List<Particle> remainingParticlesParam) {
        ticks++;
        System.out.println("tick:  " + ticks);
        for (Particle current : remainingParticlesParam) {
            current.updateCoordinates();
        }

    }

}
