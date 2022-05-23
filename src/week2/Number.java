package week2;/*
 * Number.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */


/**
 * This program identifies all numbers between 1 and 1000
 * which has the desired property. According to this property,
 * if the square of the digits of the number leads to 1 iteratively,
 * then it has the desired property.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Number {

    private static final int LOWER_LIMIT = 1; // lower limit for finding desired numbers
    private static final int UPPER_LIMIT = 1000; // upper limit for finding desired numbers

    /**
     * The main program which calls the method to find numbers with the property.
     *
     * @param args command line arguments (ignored)
     */

    public static void main(String[] args) {
        findNumbers();
    }

    /**
     * Driver function which keeps checking one by one for each number
     * by calling a recursive function.
     */

    private static void findNumbers() {
        for (int index = LOWER_LIMIT; index <= UPPER_LIMIT; index++) {
            String local = "";  //local string to check if property is achieved
            String output = ""; //output string
            findSumOfSquares(String.valueOf(index), 0, local, output);
        }
    }

    /**
     * Finds numbers with the desired property
     *
     * @param number number for which property has to be checked
     * @param sum    initially its 0, later squares of digits gets stored in it for each iteration
     * @param local  initially its empty, later it stores the squared digits sum.
     * @param output it's the final string that is displayed on screen
     */

    private static void findSumOfSquares(String number, int sum, String local, String output) {
        if (sum == 1) { //base condition to stop recursion
            System.out.println(local.substring(0, local.contains(",") ? local.indexOf(",")
                    : local.length()) + ":   " + output);
            return;
        } else if (local.contains(number)) { //base condition to stop recursion
            return;
        }
        sum = 0;
        local += local.isEmpty() ? number : "," + number;
        char[] digits = number.toCharArray(); //breaks the number into digits
        for (int index = 0; index < digits.length; index++) {
            sum += Math.pow(Integer.parseInt(String.valueOf(digits[index])), 2); //converts the digits from char to int
            output += index == 0 ? digits[index] + "^2" : " + " + digits[index] + "^2"; // appends the result into string
        }
        output += " = " + sum + (sum != 1 ? "  |  " : ""); // not appending "|" when the sum has reached 1
        findSumOfSquares(String.valueOf(sum), sum, local, output); //function is called again
    }

}//Number
