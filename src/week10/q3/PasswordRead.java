package week10.q3;/*
 * PasswordRead.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
import java.io.*;
/**
 * This class reads the password in the file
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class PasswordRead {

    /**
     * reads arguments
     * calls readPassword()
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String fileName = getArguments(args);
        readPassword(fileName);
    }

    /**
     * gets arguments
     * @param args command line arguments
     * @return filename
     */
    private static String getArguments(String[] args) {
        if (args.length == 1) {
            return args[0];
        } else {
            System.err.println("Please pass filename to read password");
            System.exit(1);
        }
        return "";
    }

    /**
     * generates hash id
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
     * reads password
     * @param fileName file name
     */
    private static void readPassword(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Password password = (Password) objectInputStream.readObject();
            if (!generateHashId(password.getPassword()).equalsIgnoreCase(password.getHashId())) {
                System.out.println("Password has been modified!");
            }
            System.out.println("Password: " + password.getPassword());
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
