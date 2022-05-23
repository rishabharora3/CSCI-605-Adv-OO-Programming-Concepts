package week11.q1;/*
 * SqrtOf3M.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This program calculates the square root of 2 using multiple threads and
 * BigDecimal objects.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class SqrtOf3M extends Thread {
    static final int UPPER_LIMIT = 20000; // defining the upper limit
    static int numberOfThreads;  // declaring the number of threads
    private int lower; // defining lower bound
    private int upper;  //  defining the upper bound
    BigDecimal sqrtValue = new BigDecimal(1);  // defining the final result

    SqrtOf3M(int lower, int upper) {  // initializing the values of constructor
        this.lower = lower;
        this.upper = upper;
    }

    /***
     * overriding the run method and calling the constructor of
     */
    @Override
    public void run() {
        this.sqrtValue = computeSquareRoot(lower, upper);
    }

    /**
     * returns the current value sqrtValue
     *
     * @return BigDecimal value of instance variable
     */
    public BigDecimal getSquareRoot() {
        return this.sqrtValue;
    }

    /***
     * computing the square root using the big decimal objects nad its functions.
     * @param lower lower bound of thread
     * @param upper upper bound of thread
     * @return BidDecimal output of each thread
     */
    private static BigDecimal computeSquareRoot(int lower, int upper) {
        BigDecimal product = new BigDecimal(1);
        for (int index = lower; index < upper; index++) {
            BigDecimal denominator = (new BigDecimal(4 * index + 1)).multiply(new BigDecimal(4 * index + 3));
            BigDecimal numerator = new BigDecimal(4 * index + 2).pow(2);
            BigDecimal result = numerator.divide(denominator, 10, RoundingMode.FLOOR);
            product = product.multiply(result);  //calculating product of independent terms
        }
        return product;
    }

    /**
     * splitting number of iteration among threads
     *
     * @param numberOfThreads argument
     * @return array of number of iterations per thread
     */
    private static int[] iterationSplit(int numberOfThreads) {
        int arr[] = new int[numberOfThreads];
        int remainder = UPPER_LIMIT;
        int threads = numberOfThreads;
        for (int i = 0; threads > 0; i++) {
            int size = (remainder + threads - 1) / threads;
            arr[i] = size;
            remainder -= size;
            threads--;
        }
        return arr;
    }


    /**
     * Creates the threads using the command line arguments
     *
     * @param args command line arguments
     * @throws InterruptedException handles interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        numberOfThreads = Integer.parseInt(args[0]);  // assigning the value from command line arguments provided
        BigDecimal product = new BigDecimal(1);
        int[] iterationsArr = iterationSplit(numberOfThreads);

        for (int index = 0; index < numberOfThreads; index++) {

            SqrtOf3M t = new SqrtOf3M(iterationsArr[index] * index,
                    (iterationsArr[index] * index) + iterationsArr[index]);  // creating thread object
            t.start();  // executing a thread
            t.join();
            product = product.multiply(t.getSquareRoot());
        }
        String result = product.toString();
        String final_string = result.substring(0, 34);
        System.out.println("Result: " + final_string);
    }
}//SqrtOf3M
