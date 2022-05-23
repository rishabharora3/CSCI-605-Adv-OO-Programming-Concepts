package week7.q3;/*
 * Chess.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * Chess is responsible for finding valid moves around the chess pieces
 * and formation of the chess board
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Chess implements ChessPieceConstants, ChessUtil {
    private static int MAX_ROWS;
    private static int MAX_COLUMNS;
    private static char[][] theBoard;
    private static char[][] theAnswerBoard;
    private static int maxNoOfPiece = 0;
    private final ChessPiece[] chess = {new Rook(this), new Bishop(this),
            new Knight(this), new Queen(this)};

    /**
     * initArguments method is for checking all given user arguments
     *
     * @param args is a String of the user input
     */
    public static void initArguments(String[] args) {
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-n")) {
                MAX_ROWS = MAX_COLUMNS = Integer.parseInt(args[index + 1]);
            }
        }
    }

    /**
     * initializeNoOfHoles method is for assigning holes to
     * the theBoard and theAnswerBoard by calling a createHole
     * function.
     *
     * @param args is a String of the user input
     */
    public static void initializeNoOfHoles(String[] args) {
        for (int index = 0; index < args.length; index++) {
            if (args[index].equals("-h")) {
                char[] x = args[index + 1].toCharArray();
                int x_COORDINATE = Character.getNumericValue(x[0] - 1);
                int y_COORDINATE = Character.getNumericValue(x[2] - 1);
                createHole(x_COORDINATE, y_COORDINATE);
            }
        }
    }

    /**
     * createHOLE method is for adding the HOLEs to the baord
     */
    public static void createHole(int x, int y) {
        theBoard[x][y] = HOLE;
        theAnswerBoard[x][y] = HOLE;
    }

    /**
     * createChessBoard method is for creating the chess baord
     */
    public static void createChessBoard() {
        theBoard = new char[MAX_ROWS][MAX_COLUMNS];
        theAnswerBoard = new char[MAX_ROWS][MAX_COLUMNS];
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLUMNS; col++) {
                theBoard[row][col] = whatColor(row, col);
                theAnswerBoard[row][col] = whatColor(row, col);
            }
        }
    }

    /**
     * whatColor method is for creating the chess baord white
     * and black space.
     */
    private static char whatColor(int row, int col) {
        if ((row + col) % 2 == 0) {
            // even spaces being white
            return 'w';
        } else {
            return 'b';
        }
    }

    /**
     * printBoard method is for printIng the chess board.
     */
    private static void printBoard() {
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLUMNS; col++) {
                System.out.print(" " + theAnswerBoard[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * createCopy method is for creating a temporary chess baord.
     */
    public char[][] createCopy() {
        char[][] tempBoard = new char[theBoard.length][];
        for (int i = 0; i < theBoard.length; i++)
            tempBoard[i] = theBoard[i].clone();
        return tempBoard;
    }

    /**
     * Main method is for calling other methods
     * inorder to find the maximum possible
     * number of ROOKs, BISHOP, Queen and Knight
     */
    public static void main(String[] args) {
        initArguments(args);
        createChessBoard();
        initializeNoOfHoles(args);
        ChessPiece[] chess = new Chess().chess;
        for (int i = 0; i < chess.length; i++) {
            chess[i].findMaxNoOfPiece(pieceSymbol[i], MAX_ROWS, MAX_COLUMNS, theBoard);
            System.out.println("Maximum Number of " + pieces[i] + " are " + maxNoOfPiece);
            printBoard();
            theAnswerBoard = theBoard;
            maxNoOfPiece = 0;
        }
    }

    /**
     * sets max answer board
     * @param tempBoard
     * @param sum
     */
    public void setAnswerBoard(char[][] tempBoard, int sum) {
        if (sum > maxNoOfPiece) {
            maxNoOfPiece = sum;
            theAnswerBoard = tempBoard;
        }
    }
}
