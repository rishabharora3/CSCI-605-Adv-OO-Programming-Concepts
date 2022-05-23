package week4.q1;/*
 * Game.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/**
 * This program identifies program which allows specifying
 * on the command line a file which contains the words and a picture.
 * After every guess the program shows n% of the image. n is
 * determined by how many characters of the word have been guessed correctly.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */


public class Game {
    private static Player playerOne;
    private static Player playerTwo;
    private static Words words;
    private static int player = 0; // player currently playing
    private static Player[] players;
    private static final Scanner scUserInput = new Scanner(System.in); // takes player inputs

    /**
     * Driver function initialising variables, starting the game and closing scanner
     *
     * @param args player names, description and text files for logos are received
     * @throws FileNotFoundException occur if file is not found
     */

    public static void main(String[] args) throws FileNotFoundException {
        init(args);
        startGame();
        closeScanner();
    }


    /**
     * getting arguments
     *
     * @param args
     * @throws FileNotFoundException
     */
    private static void getArguments(String[] args) throws FileNotFoundException {
        for (int index = 0; index < args.length; index++) {
            switch (args[index]) {
                case "-me" -> playerOne.setName(args[index + 1]);
                case "-meWords" -> playerOne.setDescription(words.getWord(args[index + 1]));
                case "-mePicture" -> playerOne.setPicture(args[index + 1]);
                case "-you" -> playerTwo.setName(args[index + 1]);
                case "-youWords" -> playerTwo.setDescription(words.getWord(args[index + 1]));
                case "-youPicture" -> playerTwo.setPicture(args[index + 1]);
            }
        }
    }

    /**
     * initialising values by parsing arguments
     *
     * @param args using arguments string array to fill different arrays
     * @throws FileNotFoundException occur if file is not found
     */

    private static void init(String[] args) throws FileNotFoundException {
        playerOne = new Player("**: your turn (##): ");
        playerTwo = new Player("**: your turn (##): ");
        words = new Words();
        getArguments(args);
        players = new Player[]{playerOne, playerTwo};
    }

    /**
     * Starts the game by letting the Player 0 guess a character
     */
    private static void startGame() {
        while (playerOne.getScore() != playerOne.getDescriptionCopy().length() && playerTwo.getScore() != playerTwo.getDescriptionCopy().length()) {
            Player currentPlayer = players[player];
            System.out.print(currentPlayer.getTurnText().replace("**", currentPlayer.getName()).replace("##", currentPlayer.getAnswerHint()));
            String characterGuessed = scUserInput.next().toLowerCase();
            if (characterGuessed.length() == 1 && currentPlayer.getDescription().contains(characterGuessed)) {
                updateScoreAndAnswer(currentPlayer, characterGuessed);
                System.out.println("\t\t" + currentPlayer.getName() + " guess was correct: " + currentPlayer.getAnswerHint());
                generateHint(currentPlayer);
                currentPlayer.updateDescription(characterGuessed);
                showWinner(currentPlayer);
            } else {
                System.out.println("Wrong Guess");
            }
            switchPlayer();
        }
    }

    /**
     * switching the player
     */
    private static void switchPlayer() {
        player = 1 - player;
    }

    /**
     * after every turn if the player guesses correctly the score is updated and
     * letter is appended in the string
     *
     * @param currentPlayer    updated score of player 0 or 1
     * @param characterGuessed character guessed by a player
     */
    private static void updateScoreAndAnswer(Player currentPlayer, String characterGuessed) {
        currentPlayer.setScore(currentPlayer.getScore() + 1);
        currentPlayer.setAnswerHint(characterGuessed);
    }

    /**
     * creating an image based on the characters guessed
     */

    private static void generateHint(Player currentPlayer) {
        Vector<String> vec = currentPlayer.getPicture().generateImage(currentPlayer.getDescriptionCopy().length(), currentPlayer.getScore());
        printVector(vec);
    }

    /**
     * printing the vector elements
     *
     * @param vec vector given
     */
    private static void printVector(Vector<String> vec) {
        for (String a : vec) {
            System.out.print(a);
        }
    }

    /**
     * once the guessing is complete, showing the word guessed by resetting description from arguments
     *
     * @param currentPlayer using argument to reset desc array
     */
    private static void showWinner(Player currentPlayer) {
        if (currentPlayer.getScore() == currentPlayer.getDescription().length()) {
            System.out.println("This word guessed correctly was: " + currentPlayer.getDescriptionCopy());
            System.out.println(currentPlayer.getName() + " has won.");
            System.out.println(players[1 - player].getName() + " has lost");
        }
    }

    /**
     * closing scanner
     */
    private static void closeScanner() {
        scUserInput.close();
    }
}//Game
