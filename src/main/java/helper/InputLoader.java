package helper;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class InputLoader {

    private static String INPUT_FILE_NAME = "dummy";
    private static Path inputFile;
    private static Scanner mainIn;

    public static void loadInput(String fileName) {

        INPUT_FILE_NAME = fileName;

        inputFile = FileSystems.getDefault().getPath("src", "main", "resources", INPUT_FILE_NAME);

        try {
            mainIn = new Scanner(inputFile).useDelimiter(", ");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Couldn't load input file [" + inputFile.toString() + "]");
        }

    }

    public static Scanner getMainIn(){
        return mainIn;
    }

    public static void closeInput() {
        mainIn.close();
    }

}
