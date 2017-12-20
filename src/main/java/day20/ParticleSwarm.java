package day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static helper.InputLoader.getMainIn;
import static helper.InputLoader.loadInput;

public class ParticleSwarm {

    private static final String INPUT_FILE_NAME = "day20_input.txt";
//    private static final String INPUT_FILE_NAME = "debug.txt";

    private static List<Particle> particles = new ArrayList<>();

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
            particles.add(Particle.parse(line));
        }

        int minAcc, minVel, minPos;

        List<Particle> minAccList = new ArrayList<>();
        List<Particle> minVelList = new ArrayList<>();
        List<Particle> minPosList = new ArrayList<>();


        // - acceleration:
        minAcc = Collections.min(particles.stream().map(Particle::getAcceleration).map(Point::getDistanceToCenter).collect
                (Collectors
                        .toSet
                                ()));
        particles.stream().forEach(entry -> {
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
            System.out.println("\n    Part 1 solution:   closest particle index = [" + particles.indexOf(minPosList
                    .get(0)
            ) + "]");
        }


    }
}
