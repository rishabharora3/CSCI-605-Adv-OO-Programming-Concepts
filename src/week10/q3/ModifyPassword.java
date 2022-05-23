package week10.q3;/*
 * ModifyPassword.java
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
 * This class modifies the password in the file
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class ModifyPassword {

    /**
     * main() reads from arguments for file name
     * calls modify password function
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String fileName = getArguments(args);
        modifyPassword(fileName);
    }

    /**
     * gets arguments from command line
     *
     * @param args command line arguments
     * @return file name
     */
    private static String getArguments(String[] args) {
        if (args.length == 1) {
            return args[0];
        } else {
            System.err.println("Please pass filename to modify password");
            System.exit(1);
        }
        return "";
    }

    /**
     * modifies password by taking input from user
     *
     * @param fileName filename
     */
    private static void modifyPassword(String fileName) {
        System.out.println("Enter password: ");
        Scanner sc = new Scanner(System.in);
        Password password = new Password(sc.nextLine());
        try {
            readFile(password, fileName);
            writeFile(password, fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads file for current password
     *
     * @param password password
     * @param fileName filename
     * @throws IOException            exceptions
     * @throws ClassNotFoundException exceptions
     */
    private static void readFile(Password password, String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream oi = new ObjectInputStream(fi);
        Password passwordRead = (Password) oi.readObject();
        password.setHashId(passwordRead.getHashId());
        oi.close();
        fi.close();
    }

    /**
     * writes new password into a file
     *
     * @param password password
     * @param fileName file name
     * @throws IOException exceptions
     */
    private static void writeFile(Password password, String fileName) throws IOException {
        FileOutputStream f = new FileOutputStream(fileName);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(password);
        o.close();
        f.close();
        System.out.println("Password Modified");
    }

}
