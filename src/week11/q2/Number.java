package week11.q2;/*
 * Number.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
import java.util.ArrayList;

/**
 * This program checks each number to the limit if it has the desired property.
 * It does so using the threads.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Number extends Thread {
    private final int start;   // declares the start and end
    private final int end;
    private final ArrayList<String> arrayListLocal; // declares an array list for local
    private static String theDetailGlobal;
    private static final ArrayList<String> arrayListGlobal = new ArrayList<>();  // declares an array list for local
    private static String type;  // type to see if local or global
    private static final String GLOBAL = "global";
    private static final String LOCAL = "local";

    public Number(int start, int end) {  // initializing the constructor
        this.start = start;
        this.end = end;
        this.arrayListLocal = new ArrayList<>();
    }

    /**
     * overriding of the run method()
     */
    @Override
    public void run() {
        for (int numberToTest = start; numberToTest <= end; numberToTest++) {  // check to see if the number has the property
            if (hasProperty(numberToTest)) {  // calls the method to check
                if (!theDetailGlobal.isEmpty()) {
                    if (LOCAL.equalsIgnoreCase(type)) {
                        arrayListLocal.add(theDetailGlobal);
                    } else {
                        arrayListGlobal.add(theDetailGlobal);
                    }
                }
            }
        }
    }

    /**
     * returns an integer array of digits of the number provided
     * @param number argument
     * @return int array
     */
    public static int[] theDigits(int number) {
        String theNumberAsString = "" + number;
        int[] asDigits = new int[theNumberAsString.length()];
        char[] asCharacters = theNumberAsString.toCharArray();
        for (int index = 0; index < asCharacters.length; index++) {
            asDigits[index] = Integer.parseInt("" + asCharacters[index]);
        }
        return asDigits;
    }

    /**
     * checks if number has the desired property
     * @param numberb argument
     * @return true if yes
     */
    public synchronized static boolean hasProperty(int number) {
        int[] theDigits = theDigits(number);  // converts into digits
        boolean[] seenThisNumber = new boolean[1000];  // if number has already been seen
        theDetailGlobal = "";
        int result = 0;
        do {
            seenThisNumber[result] = true;  // if number has been checked it is marked true
            result = 0;
            for (int theDigit : theDigits) {
                result += (int) Math.pow(theDigit, 2);
                theDetailGlobal += theDigit + "^2 + ";
            }
            theDetailGlobal = theDetailGlobal.substring(0, theDetailGlobal.length() - 3);
            theDetailGlobal += " = " + result + " | "; //appending to the string
            theDigits = theDigits(result);
        } while ((result != 1) && (!seenThisNumber[result]));
        theDetailGlobal = number + ": " + theDetailGlobal.substring(0, theDetailGlobal.length() - 2);
        return result == 1;
    }

    /**
     * returns the arraylist local
     * @return
     */
    public ArrayList<String> getArrayListLocal() {
        return arrayListLocal;
    }

    /**
     * prints local array list
     * @param numberOfThreads number of threads
     * @param playerThreadArray players array
     */
    private static void printLocalList(int numberOfThreads, Number[] playerThreadArray) {
        if (LOCAL.equalsIgnoreCase(type)) {
            System.out.println(numberOfThreads + " arraylist used");
            for (int index = 0; index < numberOfThreads; index++) {
                ArrayList<String> listLocal = playerThreadArray[index].getArrayListLocal();
                printList(listLocal);  // prints the list
            }
        }
    }

    /**
     * prints global array list
     */
    private static void printGlobalList() {
        if (GLOBAL.equalsIgnoreCase(type)) {
            System.out.println("one arraylist used");
            sortGlobalList();
            printList(arrayListGlobal);
        }
    }

    /**
     * sorts the global list using bubble sort
     */
    private static void sortGlobalList() {
        ArrayList<String> arrayListGlobal = Number.arrayListGlobal;
        int size = arrayListGlobal.size();
        for (int index = 0; index < size - 1; index++) {
            for (int index1 = 0; index1 < size - index - 1; index1++) {
                try {
                    String item1 = arrayListGlobal.get(index1).substring(0, arrayListGlobal.get(index1).indexOf(":"));
                    String item2 = arrayListGlobal.get(index1 + 1).substring(0, arrayListGlobal.get(index1 + 1).indexOf(":"));
                    if (Integer.parseInt(item1) > Integer.parseInt(item2)) {
                        String temp = arrayListGlobal.get(index1);
                        arrayListGlobal.set(index1, arrayListGlobal.get(index1 + 1));
                        arrayListGlobal.set(index1 + 1, temp);
                    }
                } catch (Exception ignored) {
                }
            }
        }
    }

    /**
     * prints items of the list provided
     * @param arrayList argument
     */
    private static void printList(ArrayList<String> arrayList) {
        for (String s : arrayList) {
            if (!s.isEmpty()) {
                System.out.println(s);
            }
        }
    }

    /**
     * asking the main thread to wait until all of the threads has executed its task
     * @param numberOfThreads argument provided
     * @param playerThreadArray players
     */
    private static void joinThreads(int numberOfThreads, Number[] playerThreadArray) {
        try {
            for (int player = 0; player < numberOfThreads; player++) {
                playerThreadArray[player].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads from the argument, creates and executes the thread
     * @param args  command line arguments
     */
    public static void main(String[] args) {
        int numberOfThreads = 0;
        int numberCount = 0;
        if (args.length == 3) {
            numberOfThreads = Integer.parseInt(args[0]);
            numberCount = Integer.parseInt(args[1]);
            type = args[2];
        } else {
            System.err.println("Please pass arguments");
            System.exit(1);
        }
        int div = numberCount / numberOfThreads;  // splitting the iterations among threads
        int start = 0;
        Number[] playerThreadArray = new Number[numberOfThreads];
        for (int index = 0; index < numberOfThreads; index++) {
            if (index == numberOfThreads - 1) {
                playerThreadArray[index] = new Number(start, numberCount);
            } else {
                playerThreadArray[index] = new Number(start, start + div);
            }
            playerThreadArray[index].start();
            start = start + div + 1;
        }
        joinThreads(numberOfThreads, playerThreadArray);  // joining all the threads
        printLocalList(numberOfThreads, playerThreadArray);  // prints the local list
        printGlobalList();  // prints the global list
    }
} // Number