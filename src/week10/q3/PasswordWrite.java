package week10.q3;/*
 * PasswordWrite.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.io.*;
import java.util.Scanner;

/**
 * This class writes the password in the file
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class PasswordWrite {

    /**
     * reads command line arguments
     *
     * @param args command line arguments
     * @return filename
     */
    private static String getArguments(String[] args) {
        if (args.length == 1) {
            return args[0];
        } else {
            System.err.println("Please pass filename to store password");
            System.exit(1);
        }
        return "";
    }

    /**
     * generates hash id
     *
     * @param password password
     * @return hash id
     */
    public static String generateHashId(String password) {
        long code = 0;
        for (int i = 0; i < password.length(); i++) {
            code = (long) (((int) password.charAt(i)) * Math.pow(31, i));
        }
        return String.valueOf(code);
    }

    /**
     * writes pass word
     *
     * @param fileName filename
     */
    private static void writePassword(String fileName) {
        System.out.println("Enter password: ");
        Scanner sc = new Scanner(System.in);
        Password password = new Password(sc.nextLine());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream o = new ObjectOutputStream(fileOutputStream);
            password.setHashId(generateHashId(password.getPassword()));
            o.writeObject(password);
            o.close();
            fileOutputStream.close();
            System.out.println("Password Stored");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    /**
     * driver method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String fileName = getArguments(args);
        writePassword(fileName);
    }
}
