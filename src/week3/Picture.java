/*
 * Picture.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
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

public class Picture {
    private static String[] description; // description of images array for two players filled using command line arguments
    private static String[] playerTurn; // text array used to show the player turn text
    private static String[] playerNames; // player name array created using command line arguments
    private static int player = 0; // player currently playing
    private static final int[] scoreArray = new int[]{0, 0}; // player guessing scores array
    private static int[] descriptionLength; // description length array since description array keeps getting updated
    private static final String[] answer = new String[]{"", ""}; // answer array to store whatever the user is guessing
    private static Vector<String>[] imageVectors; // storing image files into vector array
    private static final Scanner scUserInput = new Scanner(System.in); // takes player inputs

    /**
     * Driver function initialising variables, starting the game and closing scanner
     *
     * @param args player names, description and text files for logos are received
     * @throws FileNotFoundException occur if file is not found
     */

    public static void main(String[] args) throws FileNotFoundException {
        init(args);
        startGame(args);
        closeScanner();
    }

    /**
     * initialising values by parsing arguments
     *
     * @param args using arguments string array to fill different arrays
     * @throws FileNotFoundException occur if file is not found
     */

    private static void init(String[] args) throws FileNotFoundException {
        String currentPath = System.getProperty("user.dir") + "/";
        File[] file = new File[]{new File(currentPath + args[4]), new File(currentPath + args[1])};
        updateDescription(args);
        descriptionLength = new int[]{description[0].length(), description[1].length()};
        updateAnswerHint();
        playerTurn = new String[]{"First your turn (##): ", "Second your turn (##): "};
        playerNames = new String[]{args[3].substring(1), args[0].substring(1)};
        imageVectors = new Vector[]{addImageCharToVectorObject(file[0]), addImageCharToVectorObject(file[1])};
    }

    /**
     * adding dots(.) equal to word length to show the user hint with user text
     */

    private static void updateAnswerHint() {
        for (int i = 0; i < descriptionLength[0]; i++) {
            answer[0] += ".";
        }
        for (int i = 0; i < descriptionLength[1]; i++) {
            answer[1] += ".";
        }
    }

    /**
     * Initialisation for description array
     * or
     * description array is reset once the whole word has been guessed by one player to show
     * it on output. We remove a character one by one when the guessing game is running.
     */

    private static void updateDescription(String[] args) {
        description = new String[]{args[5].toLowerCase(), args[2].toLowerCase()};
    }

    /**
     * Starts the game by letting the Player 0 guess a character
     *
     * @param args using for checking the winner
     */

    private static void startGame(String[] args) {
        while (scoreArray[0] != descriptionLength[0] && scoreArray[1] != descriptionLength[1]) {
            System.out.print(playerTurn[player].replace("##", answer[player]));
            String characterGuessed = scUserInput.next().toLowerCase();
            if (characterGuessed.length() == 1 && description[player].contains(characterGuessed)) {
                updateScoreAndAnswer(++scoreArray[player], characterGuessed);
                System.out.println("\t\t" + playerNames[player] + " guess was correct: " + answer[player]);
                generateHint();
                description[player] = description[player].replaceFirst(characterGuessed, "");
                showWinner(args);
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
     * @param updatedScore     updated score of player 0 or 1
     * @param characterGuessed character guessed by a player
     */
    private static void updateScoreAndAnswer(int updatedScore, String characterGuessed) {
        answer[player] = answer[player].substring(0, scoreArray[player] - 1) +
                characterGuessed + answer[player].substring(scoreArray[player]);
        scoreArray[player] = updatedScore;
    }

    /**
     * creating an image based on the characters guessed
     */

    private static void generateHint() {
        Vector<String> vec = generateImage(descriptionLength[player], scoreArray[player], imageVectors[player]);
        printVector(vec);
    }

    /**
     * generates an image based on replacing n% of characters with .
     * n% refers to characterGuessed/word length * 100
     *
     * @param descLength  length of the word to guess
     * @param score       characters guessed by the user
     * @param imageVector original image file in vector
     * @return imageTempVector creating a copy of original file vector for replacing percentage
     */

    private static Vector<String> generateImage(int descLength, int score, Vector<String> imageVector) {
        Vector<String> imageTempVector = new Vector<>(imageVector);
        double percentage = (score / (double) descLength);
        int n = (int) (imageTempVector.size() - (percentage * imageTempVector.size()));
        for (int i = 0; i < n; i++) {
            int randomPos = generateRandomNumber(imageTempVector);
            if (imageTempVector.get(randomPos).contains("\n"))
                imageTempVector.set(randomPos, ".\n");
            else if (imageTempVector.get(randomPos).contains("\t\t"))
                imageTempVector.set(randomPos, "\t\t.");
            else
                imageTempVector.set(randomPos, ".");
        }
        return imageTempVector;
    }

    /**
     * adding image characters from file to vector
     *
     * @param file file of the player
     * @return vector from file
     * @throws FileNotFoundException exception when file is not found
     */

    private static Vector<String> addImageCharToVectorObject(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        Vector<String> vec = new Vector<>();
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            char[] characterArray = row.toCharArray();
            for (int i = 0; i < characterArray.length; i++) {
                if (i == characterArray.length - 1)
                    vec.add("" + characterArray[i] + "\n");
                else if (i == 0) {
                    vec.add("\t\t" + characterArray[i]);
                } else
                    vec.add("" + characterArray[i]);
            }
        }
        return vec;
    }

    /**
     * generating random number to replace the image with dots(.)
     *
     * @param vec vector given to replace
     * @return randomPos random number
     */
    private static int generateRandomNumber(Vector<String> vec) {
        Random rand = new Random();
        int randomPos;
        do {
            randomPos = rand.nextInt(vec.size());
        }
        while (vec.get(randomPos).equalsIgnoreCase("."));
        return randomPos;
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
     * @param args using argument to reset desc array
     */
    private static void showWinner(String[] args) {
        if (description[player].length() == 0) {
            updateDescription(args);
            System.out.println("This word guessed correctly was: " + description[player]);
        }
    }

    /**
     * closing scanner
     */
    private static void closeScanner() {
        scUserInput.close();
    }
}//Picture
