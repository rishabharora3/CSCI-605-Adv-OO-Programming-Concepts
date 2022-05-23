/*
 * Chess.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 *
 * Chess is responsible for finding valid moves around the chess pieces
 * and formation of the chess board
 * @author
 * @author
 *
 */
public abstract class Chess implements  Cloneable {
    static int maxRows = 8;
    static int maxCols = 8;
    static int noOfHoles = 0;
    static int[] xHole = new int[10];
    static  int[] yHole = new int[10];
    static char[] x;
    static char hole = 'H';
    static  char Rook = 'R';
    static  char Bishop = 'B';
    static  char Knight = 'N';
    static  char Queen = 'Q';
    static char[][] theBoard = new  char[1 + maxRows][1 + maxCols];
    static char[][] theAnswerBoard = new char[1 + maxRows][1 + maxCols];
    static String[] pieces = {"Rooks", "Bishops", "Knights", "Queens"};
    /**
     * initArguments method is for checking all given user arguments
     * @param args is a String of the user input
     */
    public static void initArguments(String[] args){
        for(int index = 0; index < args.length; index++){
            if(args[index].equals("-n")){
                maxRows = maxCols = Integer.parseInt(args[index + 1]);
            }else if(args[index].equals("-h")){
               x = args[index + 1].toCharArray();
               xHole[noOfHoles] = Character.getNumericValue(x[0] - 1);
               yHole[noOfHoles] = Character.getNumericValue(x[2] - 1);
               noOfHoles++;
            }
        }
    }
    /**
     * createHole method is for adding the holes to the baord
     */
    public static void createHole(){
        for (int j = 0; j < noOfHoles; j++){
           theBoard[xHole[j]][yHole[j]] = hole;
            theAnswerBoard[xHole[j]][yHole[j]] = hole;
        }

    }
    /**
     * createChessBoard method is for creating the chess baord
     */
    public static void createChessBoard(){
        for (int row = 0; row < maxRows +1; row++) {
            for (int col = 0; col < maxCols + 1; col++) {
                theBoard[row][col] = whatColor(row, col);
                theAnswerBoard[row][col] = whatColor(row, col);
            }
        }
    }
    /**
     * whatColor method is for creating the chess baord white
     * and black space.
     */
    private static char whatColor(int row, int col){
        if((row + col) % 2 == 0){
            // even spaces being white
            return 'w';
        }else{
            return 'b';
        }
    }
    /**
     * printBoard method is for printng the chess baord.
     */
    private static void printBoard(){
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                System.out.print(" " + theAnswerBoard[row][col]);
            }
            System.out.println();
        }
    }
    /**
     * createCopy method is for creating a temporary chess baord.
     */
    protected char[][] createCopy() {
        char[][] tempBoard = new char[theBoard.length][];
        for (int i = 0; i < theBoard.length; i++)
            tempBoard[i] = theBoard[i].clone();
        return tempBoard;
    }
    // findMaxNoOfPiece is an abstract method to count and place the maximum
    // number of given chess pieces
    protected abstract int findMaxNoOfPiece();
    // checkIfSafe is an abstract method to validate all the moves
    protected abstract boolean checkIfSafe(int row, int col, char[][] theTempBoard);
    // placeThePieces is an abstract method to place the given chess piece
    protected abstract void placeThePieces(char[][] tempBoard);
    /**
     * Main method is for calling other methods
     * inorder to find the maximum possible
     * number of Rooks, Bishop, Queen and Knight
     */
    public static void main(String[] args){
        initArguments(args);
        createChessBoard();
        createHole();
        Chess[] chess = {new Rook(), new Bishop(), new Knight(), new Queen()};
        for (int i = 0; i < chess.length; i++) {
            System.out.println("Maximum Number of " +pieces[i] + " are " + chess[i].findMaxNoOfPiece());
            printBoard();
            theAnswerBoard = theBoard;

        }


    }
}
