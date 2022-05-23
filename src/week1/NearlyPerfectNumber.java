package week1;/*
 * NearlyPerfectNumber.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This program prints out all nearly perfect numbers
 * between 2 and 1000. A number n is a nearly perfect number,
 * if the sum of the first k prime numbers between 2 and less than n adds up to n.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class NearlyPerfectNumber {

    private static final int LOWER_LIMIT_NPN = 2; // lower limit for finding nearly perfect numbers
    private static final int UPPER_LIMIT_NPN = 1000; // upper limit for finding nearly perfect numbers

    /**
     * The main program which calls the method to find Nearly Perfect Number.
     *
     * @param       args        command line arguments (ignored)
     */
    public static void main(String[] args) {
        findNearlyPerfectNumber();
    }

    /**
     * Given the lower and upper limit, it finds all the possible nearly perfect numbers
     * and prints them with the prime numbers combination used.
     */
    private static void findNearlyPerfectNumber() {
        int index = 2; // variable for storing the initial prime number
        int nearlyPerfectNumber = 0; // stores the nearlyPerfectNumber
        String primeNumbersComb = ""; // holds the combination of prime numbers

        // prime numbers keep adding until a nearlyPerfectNumber is found and then printed

        while (true) {
            if (isPrime(index)) {
                nearlyPerfectNumber += index;

                // appending the prime numbers in the string type variable primeNumbersComb one by one.

                primeNumbersComb = primeNumbersComb.isEmpty() ? index + "" : primeNumbersComb + " + " + index;

                // if the upper limit is reached the loop breaks

                if (nearlyPerfectNumber > UPPER_LIMIT_NPN) {
                    break;
                }

                // only when the nearlyPerfectNumber is greater than the lower limit provided, it is printed.

                else if (nearlyPerfectNumber > LOWER_LIMIT_NPN) {
                    System.out.println(nearlyPerfectNumber + "  is a nearly perfect number " + primeNumbersComb);
                }
            }
            index++;
        }
    }

    /**
     * checks if the number provided is prime.
     *
     * @param       number      int type number being checked if it is prime or not
     * @return                  returns true if the number is prime
     */
    private static boolean isPrime(int number) {
        for (int index = 2; index < number; index++) {

            // if the remainder comes out to be zero the number is not prime

            if (number % index == 0)
                return false;
        }
        return true;
    }
} // NearlyPerfectNumber
