/*
 * StringIntegerArrays.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.util.Arrays;

/**
 * This program tests the code for 2 different scenarios.
 * We have to implement the 9 operations given in questions.
 * The two scenarios are if a single argument is given or otherwise.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class StringIntegerArrays {
    static String aString, bString, cString, dString, eString,
            fString, gString, hString, iString; // declaration of all the strings on which operations have to be performed

    /**
     * The main program which calls the method which in turn calls the
     * other operation methods.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        performOperations(args);
    }

    /**
     * The performOperations function checks the length of command line argument
     * that user has provided. If only 1 command line argument is provided, if block
     * is executed or else, else block is executed.
     *
     * @param args any input possible
     */

    public static void performOperations(String[] args) {
        if (args.length == 1) {
            aString = "123425" + "25"; // string creation
            bString = "12342525";  // string creation
            cString = "5";  // string creation
            dString = "25" + "2" + cString;  // string creation
            eString = "a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";  // string creation
            fString = "A, B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";  // string creation
            gString = aString + (bString + cString) + dString;  // string creation
            hString = "2525" + "+" + "2525";  // string creation
            iString = "2525+2525";  //string creation
        } else {
            aString = "12342" + new String("" + 5) + "25";  // string creation
            bString = "12342525";  // string creation
            cString = "5";  // string creation
            dString = "" + "25" + "" + "2" + cString;  // string creation
            eString = "b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z";  // string creation
            fString = "B, C, D, E, F, G, H, I, J, K, *, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z";  // string creation
            gString = ((aString + bString) + "" + 5) + dString;  // string creation
            hString = "2525" + "+" + "2525";  // string creation
            iString = "25" + (2 + 5) + "+2525";  // string creation
        }
        System.out.println("I" + ".  " + (checkIfStringsAreEqual(aString, bString))); //checks if strings are equal in sequence
        System.out.println("II" + ". " + checkIfStringsAreIdentical(aString, bString)); //checks if strings are the same object
        System.out.println("III" + "." + (checkIfStringsAreEqual(aString, dString)));  //checks if strings are equal in sequence
        System.out.println("IV" + ". " + checkIfStringsAreIdentical(aString, dString));  //checks if strings are the same object
        System.out.println("V" + ".  " + (checkIfStringsAreEqual(hString, iString)));  //checks if strings are equal in sequence
        System.out.println("VI" + ". " + checkIfStringsAreIdentical(hString, iString));  //checks if strings are the same object
        System.out.println("VII" + "." + operationSeven(aString, dString));  //operation7() is called
        System.out.println("VIII" + "." + operationEight(eString, fString));  //operation8() is called
        System.out.println("IX" + ". " + operationNine(bString, dString));  //operation9() is called
    }

    /**
     * The function checkIfStringsAreEqual checks if both the strings
     * provided in the arguments have the same sequence
     *
     * @param a String on which operation has to be performed
     * @param b String on which operation has to be performed
     * @return returns a boolean value.
     */

    public static boolean checkIfStringsAreEqual(String a, String b) {
        return a.equalsIgnoreCase(b);
    }

    /**
     * The function checkIfStringsAreIdentical checks if both the strings
     * provided in the arguments have the same object
     *
     * @param a String on which operation has to be performed
     * @param b String on which operation has to be performed
     * @return returns a boolean value.
     */

    public static boolean checkIfStringsAreIdentical(String a, String b) {
        return (a == b);
    }

    /**
     * The function operation7() extracts from dString from the position
     * extracted from aString.
     *
     * @param aString String on which operation has to be performed
     * @param dString String on which operation has to be performed
     * @return returns a resultant string
     */

    public static String operationSeven(String aString, String dString) {
        return dString.substring(aString.charAt(0) - '0', aString.charAt(1) - '0' + 1);  //returns the substring from dString
    }

    /**
     * The function operation8() extracts the character from eString
     * which is at the position of "*" in fString.
     *
     * @param eString String on which operation has to be performed
     * @param fString String on which operation has to be performed
     * @return returns a resultant string
     */
    public static char operationEight(String eString, String fString) {
        return eString.charAt(fString.indexOf('*'));
    }

    /**
     * The function operation9() sorts the digits in bString and extract out
     * the character between 3rd Lowest and 4th Lowest number.
     * extracted from aString.
     *
     * @param bString String on which operation has to be performed
     * @param dString String on which operation has to be performed
     * @return returns a resultant string
     */
    public static String operationNine(String bString, String dString) {
        char[] bCharArray = bString.toCharArray();  //converts the bString to character array
        Arrays.sort(bCharArray);  //sorts the character array
        String str = new String(bCharArray); //converts it into another string
        char character = str.charAt(0); //extracts out the character at first position
        String[] temp = str.split(character + "*", 2);  //splits into 2 strings using regex
        character = temp[1].charAt(0);  //extracts out the first character in resultant string
        String[] temp2 = temp[1].split(character + "*", 2); ////splits into 2 strings using regex
        character = temp2[1].charAt(0);  //extracts out the first character in resultant string
        int lower = Integer.parseInt(character + ""); //gets the lower index
        String[] temp3 = temp2[1].split(character + "*", 2);
        character = temp3[1].charAt(0);
        int upper = Integer.parseInt(character + ""); //gets the upper index
        return dString.charAt(lower - 1) + " " + dString.charAt(upper - 1);  //returns the resultant output
    }
} //StringIntegerArrays
