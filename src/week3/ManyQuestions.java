/*
 * ManyQuestions.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.util.Arrays;
import java.util.Random;

/**
 * This program is the better version of the code we were
 * given with the most optimized use of ternary operator.
 * Also, there is a functionality missing to find the
 * missing numbers when the number of arguments are provided
 * in the arguments.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */


public class ManyQuestions {

    /**
     * The main program which calls the method to which calls other
     * operation methods.
     *
     * @param       args        command line arguments
     */
    public static void main(String[] args) {
        int[] numbers = new int[]{5, 1, 2, 1}; //declaration of separate integers was converted to arrays
        String aString = "abcde";  //string was initialized
        int soManyBits = 31;  //initially number of maximum bits are assigned
        if (args.length > 0)  //arguments are checked if any passed
            soManyBits = Integer.parseInt(args[0]);
        printOutput(aString, numbers); //driver function which calls the other functions
        findMissingNumber(soManyBits);  //missing functionality is called
    }

    /**
     * The function checks for the lower number and iterates from smaller digit
     * to greater digit.
     *
     * @param numberA            first number which is to be considered
     * @param numberB            second number which is to be considered
     *
     * @return                   returns a String value.
     */

    public static String sumUpFromLowerToLargerBetter(int numberA, int numberB) {
        String rValue = "";
        int lowerLimit = numberA < numberB ? numberA : numberB;
        int upperLimit = numberA < numberB ? numberB : numberA;
        for (int index = lowerLimit; index < upperLimit; index++) {
            rValue += lowerLimit + ": " + index + " ";
        }
        return rValue;
    }

    /**
     * The printBitRepresentationBetter function takes the input
     * an integer and produces a bit representation of the same
     * and returns the output in the form of string.
     *
     * @param   aInt         an integer provided for which bit
     *                       representation is given as an output
     *
     * @return               returns bit representation of integer
     *                       in string format
     */
    public static String printBitRepresentationBetter(int aInt) {
        String rValue = "";  //initializes the empty string
        for (int index = 31; index >= 0; index--) {
            rValue += ((aInt & (1 << index)) == (1 << index)) ? "1" : "0";
        }  //converts the integer into bit representation and appends it into a string
        return rValue;
    }

    /**
     * The findMaximumBetter function takes the input
     * an array of integers and produces the maximum
     * number among it using ternary operator and
     * returns the output the integer with maximum
     * value.
     *
     * @param   numbers       an array of integers provided
     *                        among which a maximum has to be
     *                        taken out
     *
     * @return                returns integer with maximum value.
     */
    public static int findMaximumBetter(int[] numbers) {
        return ((numbers[0] > numbers[1]) ? ((numbers[0] > numbers[2]) ? ((numbers[0] > numbers[3]) ? numbers[0] : numbers[3])
                : ((numbers[2] > numbers[3]) ? numbers[2] : numbers[3]))
                : ((numbers[1] > numbers[2]) ? ((numbers[1] > numbers[3]) ? numbers[1] : numbers[3]) : ((numbers[2] > numbers[3]) ? numbers[2] : numbers[3])));
    }


    /**
     * The reverseStringBetter function takes the input
     * of string and gives the reversed string as the output.
     *
     * @param   original      a string which has to be reversed.
     *
     * @return                returns the reversed string.
     */
    public static String reverseStringBetter(String original) {
        char[] tempArray = original.toCharArray();  //converts string into character array
        int length = original.length();  //calculates the length of string provided
        for (int index = 0; index < length / 2; index++) {
            char tmp = tempArray[index]; //swaps the element from index and length -1 -index
            tempArray[index] = tempArray[length - 1 - index];
            tempArray[length - 1 - index] = tmp;
        }
        return String.valueOf(tempArray);  //converts the array into string and returns
    }

    /**
     * The findMissingNumber function takes the number of bits
     * as the input and produces the random integer and then finds it.
     *
     * @param   bitsUsed      a string which has to be reversed.
     */
    public static void findMissingNumber(int bitsUsed) {
        long maxNum = (long) Math.pow(2, bitsUsed);  //calculates the maximum number from the bits provided
        long numberMissing = new Random().nextInt((int) Math.pow(2, bitsUsed));  //generates the random integer
        for (long index = 1; index < maxNum; index++) {
            if (index == numberMissing) {  //searches for the integer that is missing.
                System.out.println("Found Missing: " + index);
            }
        }
    }



    /**
     * The printOutput function calls all the other functions and
     * prints out the required result.
     *
     * @param  aString            string on which reverse operation has
     *                            to be performed
     * @param  numbers            an array of numbers on which operation
     *                            has to be performed
     */

    private static void printOutput(String aString, int[] numbers) {
        System.out.println("sumUpFromLowerToLargerBetter(" + numbers[0] + "," + numbers[1] + ") = " +
                sumUpFromLowerToLargerBetter(numbers[0], numbers[1]));  //calls the function
        System.out.println("printBitRepresentationBetter(" + numbers[0] + "): " + printBitRepresentationBetter(numbers[0]));
        System.out.println("findMaximumBetter(" + Arrays.toString(numbers).
                replace("[", "").replace("]", "") + ") = " +
                findMaximumBetter(numbers));
        System.out.println("reverseStringBetter(" + aString + ") = " + reverseStringBetter(aString));
    }
} //ManyQuestions