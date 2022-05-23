package week11.q3;/*
 * GrabIt.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
//package week11assignment.q3;

import java.util.Scanner;

/**
 * This program calculates the number of marbles fro each player
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class GrabIt extends Thread {
    private static int totalTurns = 100;  // defining total number of terms
    private static final Object marbleAvailable = new Object();  // creating the object
    private final int player;  // declaring tha player
    private int marblesGrabbed = 0;  // number of marbles grabbed
    private static int maxMarblesGrabbed = 0;  // taking into account maximum marbles grabbed
    private static int playerName = 0;  // initializing the player

    public GrabIt(int player) {
        this.player = player;
    }  // initializing the final variable

    /**
     * ovverriding the run method
     */
    public void run() {
        grabMarble(); // calling the method which computes the marble.
    }

    /**
     * this method counts the number of marble grabbed by a player
     * provided turns are within the limit
     */
    private void grabMarble() {
        while (totalTurns > 0) {
            try {
                sleep((int) (10 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (marbleAvailable) {  // synchronization block
                if (totalTurns > 0) {  //so that only one thread has access to it.
                    totalTurns--;
                    marblesGrabbed++;
                } else
                    break;
            }
        }
        System.out.println("player " + player + "    grabbed so many:    " + marblesGrabbed + " marbles   ");
        setMaxMarblesGrabbed(player, marblesGrabbed);  // calling the function to keep track of marbles grabbed
    }

    /**
     * this method checks for the maximum marbles grabbed by player and updating it.
     *
     * @param player         current player
     * @param marblesGrabbed number of marbles grabbed
     */
    private void setMaxMarblesGrabbed(int player, int marblesGrabbed) {
        if (marblesGrabbed > maxMarblesGrabbed) {
            maxMarblesGrabbed = marblesGrabbed;
            playerName = player;
        }
    }

    /**
     * showPlayerWon() prints the player with maximum marbles
     */
    private static void showPlayerWon() {
        System.out.println("Player " + playerName + " won\nMarbles grabbed: " + maxMarblesGrabbed);
    }

    /**
     * creates and executes the thread.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Enter Player count: ");  // asking the user for player count
        Scanner sc = new Scanner(System.in);
        int playerCount = sc.nextInt();
        Thread[] playerThreadArray = new Thread[playerCount];  // creating the array of thread objects
        for (int player = 0; player < playerCount; player++) {
            playerThreadArray[player] = new GrabIt(player);  // creating the threads
            playerThreadArray[player].start();  // executing the thread
        }
        try {
            for (int player = 0; player < playerCount; player++) {
                playerThreadArray[player].join();  // asking the main() thread to wait
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showPlayerWon();
    }
} // GrabIt
