package y2018.day9;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helper.InputLoader.*;

public class MarbleMania {

    private static final String INPUT_FILE_NAME = "year_2018/day9_input.txt";
    //    private static final String INPUT_FILE_NAME = "debug.txt";
    private List<Integer> circle;
    private int[] playersScore;
    private static int numberOfPlayers = -1;
    private static int lastMarbleWorths = -1;
    private static int currentMarbleIndex = -1;


    public static void main(String[] args) {
        System.out.println("----   ADVENT Of code   2018    ----");
        long start = new Date().getTime();
        System.out.println("\n:::START = " + start);
        System.out.println("\n                ---=== Day 9 ===---     ");
        System.out.println("                 - Marble Mania -     ");

        System.out.println("\n    ---=== Part 1 ===---     ");

        MarbleMania myClass = new MarbleMania();

        myClass.partOne();


        long p2Start = new Date().getTime();
        System.out.println("\nP1 Duration: " + (p2Start - start) + "ms (" + (p2Start - start) / 1000 + "s)");

        System.out.println("=========================================================================================");
        System.out.println("\n    ---=== Part 2 ===---     ");


        myClass.partTwo();


        long end = new Date().getTime();
        System.out.println("\nP2 Duration: " + (end - p2Start) + "ms (" + (end - p2Start) / 1000 + "s)");
        System.out.println("==========");
        System.out.println("\nTotal Duration: " + (end - start) + "ms (" + (end - start) / 1000 + "s)");

        System.out.println("\n:::END = " + end);
    }

    private void partOne() {

        parseInput(); // initializes numberOfPlayers and lastMarbleWorths
        playersScore = new int[numberOfPlayers];
        circle = new LinkedList<>();

        playTheGame();

        System.out.println("\n    Part 1 solution:   the winning Elf's score= [" + Arrays.stream(playersScore).max().getAsInt() + "]");

    }

    private void playTheGame() {
        // put the starting marbleValue '0' and make it the 'currentMarbleIndex'
        int marbleValue = 0;
        circle.add(marbleValue);
        currentMarbleIndex = 0; //this is the index of the marbleValue in the circle list

        int whilche = 0;
        while (marbleValue < lastMarbleWorths) {
            whilche++;
            if (whilche % 1000 == 0) System.out.print("\nwhile => " + whilche);
            if (whilche % 100 == 0) System.out.print(".");

            for (int playerIndex = 0; playerIndex < numberOfPlayers && marbleValue < lastMarbleWorths; playerIndex++) {
                marbleValue++;
//                playTurn(playerIndex, marbleValue);
                playTurnV2(playerIndex, marbleValue);
//                System.out.print(".");
//                System.out.println("    circle = " + circle);
//                System.out.println("    playersScore = " + Arrays.toString(playersScore));
            }
        }
    }

    /**
     * Game logic here:
     * <p>
     * 1. Place the marble between the marbles that are 1 and 2 marbles clockwise of the current marble.
     * The marble that was just placed then becomes the current marble.
     * <br/>
     * <p>
     * 2. If marble is a multiple of 23:
     * <br/> - the current player keeps the marble they would have placed, adding it to their score.
     * <br/> - in addition, the marble 7 marbles counter-clockwise from the current marble is removed
     * from the circle and also added to the current player's score.
     * <br/> - The marble located immediately clockwise of the marble that was removed becomes the new current marble.
     * <p>
     *
     * @param playerIndex the player index in the players array
     * @param marbleValue the value/score of a marble
     */
    private void playTurn(int playerIndex, int marbleValue) {
//        System.out.println(String.format("Playing turn..., player= %d , marble/turn= %d", playerIndex + 1, marbleValue));

        if ((marbleValue % 23) != 0) { // common case (marble is NOT a multiple of 23
//            System.out.println("    common case ...");
//            1. Place the marble between the marbles that are 1 and 2 marbles clockwise of the current marble.
//            * The marble that was just placed then becomes the current marble.

            int realIndex = 1;
            if (currentMarbleIndex + 2 <= circle.size()) { // place it where it should be if somewhere in the middle
                realIndex = currentMarbleIndex + 2;
            }

            circle.add(realIndex, marbleValue);
            currentMarbleIndex = realIndex;
//            System.out.println("    currentMarbleIndex = " + currentMarbleIndex);
        } else { // uncommon case
//            System.out.println("    UNCOMMON case ...");
//          * <br/> - the current player keeps the marble they would have placed, adding it to their score.
            playersScore[playerIndex] += marbleValue;
//          * <br/> - in addition, the marble 7 marbles counter-clockwise from the current marble is removed
            int takeMarbleIndex = currentMarbleIndex - 7;
            if (takeMarbleIndex < 0) {
                takeMarbleIndex = circle.size() - Math.abs(takeMarbleIndex);
            }
//            System.out.println("    takeMarbleIndex = " + takeMarbleIndex);
//          * from the circle and also added to the current player's score.
            playersScore[playerIndex] += circle.get(takeMarbleIndex);
            circle.remove(takeMarbleIndex);
//          * <br/> - The marble located immediately clockwise of the marble that was removed becomes the new current marble.
            currentMarbleIndex = takeMarbleIndex;
//            System.out.println("    currentMarbleIndex = " + currentMarbleIndex);

        }

    }

    private void playTurnV2(int playerIndex, int marbleValue) {

        if ((marbleValue % 23) != 0) {
//            System.out.print("rotate -1 : ");
            Collections.rotate(circle, -1);// set the current marble to last position in list
            circle.add(marbleValue);

//            System.out.println(circle);

        } else {
//            System.out.print("take 7th  : ");
            Collections.rotate(circle, 7);
            playersScore[playerIndex] += circle.remove(circle.size() - 1) + marbleValue;
            Collections.rotate(circle, -1);

//            System.out.println(circle);
        }


    }


    private void partTwo() {

        parseInput(); // initializes numberOfPlayers and lastMarbleWorths
        playersScore = new int[numberOfPlayers];
        lastMarbleWorths = lastMarbleWorths * 100;
        circle = new LinkedList<>();

        playTheGame();


        System.out.println("\n    Part 2 solution:   winning Elf's score if the number " +
                "of the last marble is 100 times larger= [" + Arrays.stream(playersScore).max().getAsInt() + "]");
    }

    /**
     * Parse the input and initialize the class fields:
     * <br/>y2018.day9.MarbleMania#numberOfPlayers
     * <br/>and
     * <br/>y2018.day9.MarbleMania#numberOfMarbles
     */
    private void parseInput() {
        loadInput(INPUT_FILE_NAME, "");
        String inputRegex = "(\\d+) players; last marble is worth (\\d+) points";
        Pattern numberPattern = Pattern.compile(inputRegex);
        String input = getMainIn().nextLine();
        Matcher m = numberPattern.matcher(input);

        if (m.matches()) {
            numberOfPlayers = Integer.valueOf(m.group(1));
            lastMarbleWorths = Integer.valueOf(m.group(2));
        } else {
            throw new Error("Input string [" + input + "] does not match the provided regex [" + inputRegex + "]");
        }


        closeInput();
    }

}
