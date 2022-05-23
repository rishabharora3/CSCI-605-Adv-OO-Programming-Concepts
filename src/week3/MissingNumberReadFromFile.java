/*
 * MissingNumberReadFromFile.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


/**
 * This program takes the argument and reads a file and
 * finds the missing number.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */


public class MissingNumberReadFromFile {

    /**
     * The main program which calls the method to which
     * finds out the missing number using an algorithm.
     *
     * @param       args        command line arguments
     */
    public static void main(String[] args) {
        String data = init(args);
        findMissingNumber(Integer.parseInt(args[1]), data);
    }

    /**
     * The init function initializes the file object
     * and appends its content into a string and returns it.
     *
     * @param       args        arguments which were passed
     *                          in command line.
     *
     * @return                  returns the output string
     */
    public static String init(String[] args) {
        String data = "";  //initializes the string
        Scanner sc = null;  //initializes the scanner object to null
        try {
            String currentPath = System.getProperty("user.dir") + "/";
            File file = new File(currentPath + args[3]); //initializes the file object
            sc = new Scanner(file); //
            while (sc.hasNextLine()) {
                data += "||" + (sc.nextLine()) + "||";  //appends the data into string from file
            }
        } catch (FileNotFoundException ignored) {

        } finally {
            if (sc != null)
                sc.close();
        }
        return data;
    }

    /**
     * The findMissingNumber function which checks for
     * the number missing and prints it out.
     *
     * @param       data        String in which number has to be searched.
     */
    public static void findMissingNumber(int n, String data) {
        long maximumNumber = (long) Math.pow(2, n);  //calculates the maximum number
        for (int index = 1; index < maximumNumber; index++) {
            if (!data.contains("||" + index + "||")) {  //performs a contains check for every number to the maximum number
                System.out.println("Found missing: " + index);
            }
        } //MissingNumberReadFromFile
    }
}