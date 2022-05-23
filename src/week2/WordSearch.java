package week2;/*
 * WordSearch.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */


/**
 * This program searches for a group of substrings in an array
 * of string, and it prints out the row and column where the
 * substring is found. This program searches forwards, backwards,
 * upwards and downwards in the given puzzle string array.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class WordSearch {

    private static final String[] puzzle = {"tomato",       // 6 x 6
            "omelet",
            "carrot",
            "onions",
            "garlic",
            "bagels"
    }; // string array in which substrings are to be searched.
    private static final String[] wordsToSearchFor = {"to", "me", "ma", "on"}; //substrings which are to be found.
    private static final int ROW_LENGTH = puzzle.length; //length of each word in substring row wise
    private static final int COLUMN_LENGTH = puzzle[0].length(); //length of each word column wise
    private static final char[][] puzzleMatrix = new char[ROW_LENGTH][COLUMN_LENGTH]; // storing conversion to matrix

    /**
     * The main program which calls the method to print puzzle, create matrix and search word.
     *
     * @param arguments command line arguments(ignored)
     */

    public static void main(String[] arguments) {
        printPuzzle();  // prints the puzzle string[] required in the output
        createMatrix(); // creates a matrix and stores the characters from String in puzzle
        searchWord();   // the driver function to search substrings
    }

    /**
     * printPuzzle() method displays the method on screen
     */

    private static void printPuzzle() {
        for (int index = 0; index < puzzle.length; index++) {
            System.out.println(index + ":   " + puzzle[index]);
        }
    }

    /**
     * createMatrix() method creates matrix and stores
     * characters from the string[] given.
     */

    private static void createMatrix() {
        for (int index = 0; index < puzzleMatrix.length; index++) {
            puzzleMatrix[index] = puzzle[index].toCharArray();  //converts string to characters and stores them as row
        }
    }

    /**
     * Driver function which keeps checking one by one for each substring
     * by calling different functions as and when required.
     */

    private static void searchWord() {
        for (String word : wordsToSearchFor) {
            System.out.println("Searching for: " + word);
            String rowsFound = ""; //empty string where result row will be appended
            rowsFound = searchForward(word, rowsFound); //searches forward
            searchBackward(word, rowsFound); //searches backward
            String columnsFound = "";  //empty string where result column will be appended
            columnsFound = searchUpward(word, columnsFound); //searches upward
            searchDownward(word, columnsFound); //searches downward
        }
    }

    /**
     * searchForward function which keeps checking row by row for each substring
     * in the forward direction and appends it in the rowsFound
     *
     * @param word      word that is to be searched
     * @param rowsFound initially empty string, later result row will be appended
     * @return returns string rowFound
     */

    private static String searchForward(String word, String rowsFound) {
        for (int row = 0; row < ROW_LENGTH; row++) {
            if (puzzle[row].contains(word)) {
                System.out.println("Found \"" + word + "\" in row: " + row + " " + puzzle[row]);
                rowsFound += row;
            }
        }
        return rowsFound;
    }

    /**
     * searchBackward function which keeps checking row by row for each substring
     * in the backward direction and appends it in the rowsFound if it doesn't exist already
     *
     * @param word      word that is to be searched
     * @param rowsFound result row will be appended
     */

    private static void searchBackward(String word, String rowsFound) {
        for (int row = 0; row < ROW_LENGTH; row++) {
            if (rowsFound.contains(String.valueOf(row)))
                continue;
            String wordBackward = "";
            for (int column = COLUMN_LENGTH - 1; column >= 0; column--) {
                wordBackward += puzzleMatrix[row][column];
            }
            if (wordBackward.contains(word)) {
                System.out.println("Found \"" + word + "\" in row: " + row + " " + wordBackward);
            }
        }
    }


    /**
     * searchUpward function which keeps checking col by col for each substring
     * in the upward direction and appends it in the columnsFound
     *
     * @param word         word that is to be searched
     * @param columnsFound initially empty string, later result column will be appended
     * @return returns string columnsFound
     */
    private static String searchUpward(String word, String columnsFound) {
        for (int column = 0; column < COLUMN_LENGTH; column++) {
            String wordUpward = "";
            for (int row = ROW_LENGTH - 1; row >= 0; row--) {
                wordUpward += puzzleMatrix[row][column];
            }
            if (wordUpward.contains(word)) {
                System.out.println("Found \"" + word + "\" in column: " + column + " " + wordUpward);
                columnsFound += columnsFound.isEmpty() ? column : "," + column;
            }
        }
        return columnsFound;
    }

    /**
     * searchDownward function which keeps checking col by col for each substring
     * in the downward direction and appends it in the columnsFound if it doesn't exist already
     *
     * @param word         word that is to be searched
     * @param columnsFound result column will be appended
     */

    private static void searchDownward(String word, String columnsFound) {
        for (int column = 0; column < COLUMN_LENGTH; column++) {
            if (columnsFound.contains(String.valueOf(column)))
                continue;
            String wordDownward = "";
            for (int row = 0; row < ROW_LENGTH; row++) {
                wordDownward += puzzleMatrix[row][column];
            }
            if (wordDownward.contains(word)) {
                System.out.println("Found \"" + word + "\" in column: " + column + " " + wordDownward);
            }
        }
    }
}//WordSearch
