package week1;/*
 * Water.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.util.*;

/**
 * This program finds out if it can completely fill the
 * empty bucket using all the water of some buckets using the
 * maximum or minimum number of buckets. If no commandline argument
 * is supplied, then the program finds the maximum amount of buckets,
 * otherwise the minimum amount of buckets.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

class Water {

    private static int maxOrMinBuckets; // storing max or min buckets number
    private static String maxOrMinBucketsComb; // storing the combination of buckets
    private static final int[] myVolumes = {1, 1, 2, 4, 5, 6}; // initial bucket volumes
    private static final int soManyBuckets = myVolumes.length; // length of initial bucket volumes
    private static final int[] bucketsToFill = {1, 2, 3, 4, 6, 7, 8, 9}; // empty buckets we need to fill
    private static boolean largestAmountOfUsedBuckets = true; // boolean to check whether to find max or min

    /**
     * The main program which calls the method to find Nearly Perfect Number.
     *
     * @param arguments command line arguments
     */
    public static void main(String[] arguments) {
        init(arguments);
        findAndPrintMaxOrMinBuckets();
    }

    /**
     * The init function checks if a user has provided a command line argument
     * sets the max or min initial value based on the command line argument length
     * sorts the array
     *
     * @param arguments any input possible
     */
    private static void init(String[] arguments) {
        if (arguments.length > 0)
            largestAmountOfUsedBuckets = false;
        setMaxOrMinInitialValue();

        // sorting the array

        Arrays.sort(myVolumes);

        System.out.println("Available full bucket volumes: " + Arrays.toString(myVolumes));
    }

    /**
     * Driver function which keeps checking one by one the combination for the empty buckets
     * using the given buckets
     */
    private static void findAndPrintMaxOrMinBuckets() {
        String localComb = ""; // stores local combination

        for (int K : bucketsToFill) {

            findCombinations(0, 0, K, localComb);
            if (maxOrMinBucketsComb == null || maxOrMinBucketsComb.isEmpty())
                System.out.println(K + "l:     can not be done");
            else
                System.out.println(K + "l:     " + maxOrMinBucketsComb);

            //once the combination is found, resetting the variables

            setMaxOrMinInitialValue();
            maxOrMinBucketsComb = "";
        }
    }

    /**
     * sets max or min buckets initial value
     */
    private static void setMaxOrMinInitialValue() {
        if (largestAmountOfUsedBuckets)
            maxOrMinBuckets = Integer.MIN_VALUE;
        else
            maxOrMinBuckets = Integer.MAX_VALUE;
    }

    /**
     * Finds combinations using the buckets and stores the max or min bucket
     * combination found
     *
     * @param index            initial index to start the recursion from
     * @param sum              keep adding the different buckets to find combination
     * @param emptyBucket      the empty bucket looking the combination for
     * @param combinationLocal using the local string variable to store different cobinations
     */
    private static void findCombinations(int index, int sum, int emptyBucket,
                                         String combinationLocal) {

        // base condition for the recursion, checking the sum after every bucket get included

        if (sum == emptyBucket) {
            findMaxOrMinBuckets(emptyBucket, combinationLocal);
            return;
        }

        for (int i = index; i < soManyBuckets; i++) {

            // if the bucket being added makes the sum greater than the required, it is ignored

            if (sum + myVolumes[i] > emptyBucket)
                continue;

            // appends a bucket in combinationLocal

            combinationLocal += combinationLocal.isEmpty() ? myVolumes[i] : "," + myVolumes[i];

            // initial call for recursion

            findCombinations(i + 1, sum + myVolumes[i], emptyBucket,
                    combinationLocal);

            // removing the bucket once it has been used

            combinationLocal = combinationLocal.contains(",") ? combinationLocal.substring(0, combinationLocal.lastIndexOf(",")) : "";
        }
    }

    /**
     * Finding the combination by looping and storing it if it is the desired maximum or minimum combination
     *
     * @param emptyBucket      the bucket size we are looking for
     * @param combinationLocal storing combination found
     */
    private static void findMaxOrMinBuckets(int emptyBucket, String combinationLocal) {
        int index; // loop iterator variable
        String output = "yes: " + emptyBucket + "l = "; // output string
        String[] temp = combinationLocal.isEmpty() ? new String[0] : combinationLocal.split(",");

        // running the loop in reverse to print in decending order

        for (index = temp.length - 1; index >= 0; index--) {
            if (index != temp.length - 1)
                output = output + " + ";
            output = output + temp[index] + "l";
        }
        setMaxOrMinBuckets(output, temp);
    }

    /**
     * Setting the max or min combination found in a global variable
     *
     * @param output combination to be shown to the user
     * @param temp   array being used to store combination after parsing the string
     */
    private static void setMaxOrMinBuckets(String output, String[] temp) {
        if (temp.length != 0) {
            if (largestAmountOfUsedBuckets && temp.length > maxOrMinBuckets) {
                maxOrMinBuckets = temp.length;
                maxOrMinBucketsComb = output;
            } else if (!largestAmountOfUsedBuckets && temp.length < maxOrMinBuckets) {
                maxOrMinBuckets = temp.length;
                maxOrMinBucketsComb = output;
            }
        }
    }
} // Water