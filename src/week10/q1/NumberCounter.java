package week10.q1;/*
 * NumberCounter.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * This class reads from the file and count the occurrences of numbers between
 * 1 and 80.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class NumberCounter {
    static final int[] numberOccurrences = new int[80];  // creating an array to store the occurrences

    /**
     * this method gets the absolute path and returns it
     *
     * @param args command line arguments
     * @return absolute path
     */
    private static String processFilePath(String args) {
        String path = System.getProperty("user.dir") + "/";
        System.out.println(path + args);
        return path + args;
    }

    /**
     * checkFileExtension checks the extension of file and processes accordingly
     *
     * @param path absolute path provided
     * @return Reader after processing the file
     * @throws IOException throws IoException
     */
    private static Reader checkFileExtension(String path) throws IOException {
        Reader reader;
        if (path.contains("gz")) {  // checks if file is zipped, then extracts it
            GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(path));
            reader = new InputStreamReader(gzip);
        } else {  // else directly reads it
            reader = new FileReader(path);
        }
        return reader;  // returns the reader
    }

    /**
     * printArray() prints the result in the desired format.
     */
    private static void printArray() {
        for (int index = 0; index < 80; index++) {
            if (index < 10)
                System.out.print(index + 1 + ":  " + numberOccurrences[index] + " ");
            else
                System.out.print(index + 1 + ": " + numberOccurrences[index] + " ");
            if ((index + 1) % 4 == 0)
                System.out.println();
        }
    }

    /**
     * countFrequency is the main method which counts the occurrences of
     * numbers which are read from the file
     *
     * @param path absolute path provided
     */
    private static void countFrequency(String path) {
        int indexArray;
        try (BufferedReader bufferedReader = new BufferedReader(checkFileExtension(path))) {
            for (String line : bufferedReader.lines().toList()) {
                // splitting using comma and then space
                for (String number : line.split(",")[1].split(" ")) {
                    try {
                        indexArray = Integer.parseInt(number);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    numberOccurrences[indexArray - 1]++;  // increasing the occurrence
                }
            }
            printArray(); // calls the function which prints the array
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main() reads from arguments or takes input from stdin.
     * calls the function to process file.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            String path = processFilePath(args[0]);
            countFrequency(path);
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.println("File not provided in the arguments, please provide the file name:");
            String fileName = sc.nextLine();
            String path = processFilePath(fileName);
            countFrequency(path);
        }
    }

}
