package week4.q2;
/*
 * Grep.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This program identifies patterns
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Grep {

    public static void main(String[] args) throws FileNotFoundException {
        init(args);
    }

    private static void init(String[] args) throws FileNotFoundException {
        readFile(args[0]);
    }

    public static void readFile(String fileName) throws FileNotFoundException {
        String currentPath = System.getProperty("user.dir") + "/";
        File file = new File(currentPath + fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            if (!word.isEmpty()) {
                System.out.println("Line:" + Pattern.matchPattern(word) + " " + word);
            }
        }
    }
}//Grep
