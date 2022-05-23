package week1;/*
 * Board.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This program prints a board to determine the maximum number of kings we
 * can put on the not traditional nxn chess board, so such no king is not
 * in check. The chess board has squares on it on which a king can not be place.
 * You can only put a king on the squares white or black square.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Board {

    int MAX_ROWS = 6; // max rows in the matrix
    int MAX_COLUMNS = 6; // min rows in the matrix
    char[][] theBoard = new char[1 + MAX_ROWS][MAX_COLUMNS]; // initial board
    char[][] answerBoard = new char[MAX_ROWS][MAX_COLUMNS]; // answer board found
    private int maxNofKings; // max no of kings found
    private final char NOT_LEGAL = '.'; // walls

    /**
     * The main program which calls the method to find max kings matrix.
     *
     * @param args command line arguments (ignored)
     */
    public static void main(String[] args) {
        Board board = new Board();
        board.initTheBoard();
        board.createWall();
        board.findMaxKingsMatrix();
        System.out.println("% java Board\nmaximum number of kings = " + board.maxNofKings);
        board.printBoard(board.answerBoard);
    }

    /**
     * initialization of board
     */
    public void initTheBoard() {
        maxNofKings = 0;
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                theBoard[row][column] = whatColor(row, column);
            }
        }
    }


    /**
     * @param row    row given
     * @param column column given
     * @return if even provides white and black if odd
     */
    private char whatColor(int row, int column) {
        if ((row + column) % 2 == 0)
            return 'w';
        else
            return 'b';
    }

    /**
     * wall creation
     */
    public void createWall() {
        // theBoard[2][0] = NOT_LEGAL;
        theBoard[2][1] = NOT_LEGAL;
        theBoard[2][2] = NOT_LEGAL;
        theBoard[2][3] = NOT_LEGAL;
        theBoard[4][1] = NOT_LEGAL;
        theBoard[4][2] = NOT_LEGAL;
        theBoard[4][3] = NOT_LEGAL;
        theBoard[3][1] = NOT_LEGAL;
        theBoard[3][3] = NOT_LEGAL;
        for (int index = 0; index < MAX_ROWS - 1; index += 2) {
            theBoard[1][index] = NOT_LEGAL;
            theBoard[MAX_ROWS - 2][index] = NOT_LEGAL;
        }
    }

    /**
     * Prints the board
     *
     * @param board 2d array to be printed
     */
    public void printBoard(char[][] board) {
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                System.out.print(board[row][column] + "   ");
            }
            System.out.println();
        }
    }

    /**
     * Initializes a K at one postion and creates a matrix accordingly
     * finds all combinations
     */
    public void findMaxKingsMatrix() {
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (checkConditions(row, column, theBoard)) {
                    char[][] tempBoard = createCopy();
                    tempBoard[row][column] = 'K';
                    placeKings(tempBoard);
                }
            }
        }
    }

    /**
     * Creates a clone of the orginal matrix
     *
     * @return a copy of original board
     */
    private char[][] createCopy() {
        char[][] tempBoard = new char[theBoard.length][];
        for (int i = 0; i < theBoard.length; i++)
            tempBoard[i] = theBoard[i].clone();
        return tempBoard;
    }

    /**
     * place kings on every board provided with initial K values
     * always starts from 0,0
     *
     * @param tempBoard new board created for every new combination
     */
    public void placeKings(char[][] tempBoard) {
        int sum = 1;
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (checkConditions(row, column, tempBoard)) {
                    tempBoard[row][column] = 'K';
                    sum++;
                }
            }
        }
        if (sum > maxNofKings) {
            maxNofKings = sum;
            answerBoard = tempBoard;
        }
    }

    /**
     * checks condtions before placing K
     *
     * @param row      row provided
     * @param column   column provided
     * @param theBoard board provided
     * @return if neighbours are not K
     */
    private boolean checkConditions(int row, int column, char[][] theBoard) {
        return !(theBoard[row][column] == NOT_LEGAL) &&
                !(theBoard[row][column] == 'K') &&
                !(row - 1 > -1 && theBoard[row - 1][column] == 'K') &&
                !(row + 1 < MAX_ROWS && theBoard[row + 1][column] == 'K') &&
                !(column - 1 > -1 && theBoard[row][column - 1] == 'K') &&
                !(column + 1 < MAX_COLUMNS && theBoard[row][column + 1] == 'K') &&
                !(row + 1 < MAX_ROWS && column + 1 < MAX_COLUMNS && theBoard[row + 1][column + 1] == 'K') &&
                !(row - 1 > -1 && column - 1 > -1 && theBoard[row - 1][column - 1] == 'K') &&
                !(row - 1 > -1 && column + 1 < MAX_COLUMNS && theBoard[row - 1][column + 1] == 'K') &&
                !(row + 1 < MAX_ROWS && column - 1 > -1 && theBoard[row + 1][column - 1] == 'K');
    }


} // Board
