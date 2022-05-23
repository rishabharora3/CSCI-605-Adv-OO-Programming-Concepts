package week7.q2;/*
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
public abstract class Chess {
    protected static int MAX_ROWS;
    protected static int MAX_COLUMNS;
    protected static char HOLE = 'H';
    protected static char ROOK = 'R';
    protected static char BISHOP = 'B';
    protected static char KNIGHT = 'N';
    protected static char QUEEN = 'Q';
    private static char[][] theBoard ;
    protected static char[][] theAnswerBoard;
    private static final String[] pieces = {"Rooks", "Bishops", "Knights", "Queens"};
    private static final char[] pieceSymbol = {'R', 'B', 'N', 'Q'};
    protected static int maxNoOfPiece = 0;

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
     * createHOLE method is for adding the HOLEs to the board
     *
     * @param x  x_coordinate of the hole
     * @param y  y_coordinate of the hole
     */
    public static void createHole(int x, int y) {
        theBoard[x][y] = HOLE;
        theAnswerBoard[x][y] = HOLE;
    }

    /**
     * createChessBoard method is for creating the chess board
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
     * whatColor method is for creating the chess board white
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
     * printBoard method is for printing the chess board.
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
     * createCopy method is for creating a temporary chess board.
     */
    protected char[][] createCopy() {
        char[][] tempBoard = new char[theBoard.length][];
        for (int i = 0; i < theBoard.length; i++)
            tempBoard[i] = theBoard[i].clone();
        return tempBoard;
    }


    /**
     * findMaxNoOfPiece method loops through the board to find the position
     * of the first position of the piece.
     *
     * @param piece  piece that has to be placed on the board
     * @return maxNoOfPiece  returns the maximum number of the particular piece.
     */
    protected int findMaxNoOfPiece(char piece) {
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLUMNS; col++) {
                if (!(theBoard[row][col] == HOLE)) {
                    if (checkIfSafe(row, col, theBoard)) {
                        char[][] tempBoard = createCopy();
                        tempBoard[row][col] = piece;
                        placeThePieces(tempBoard);
                    }
                }
            }
        }
        return maxNoOfPiece;
    }

    // checkIfSafe is an abstract method to validate all the moves
    protected abstract boolean checkIfSafe(int row, int col, char[][] theTempBoard);

    // placeThePieces is an abstract method to place the given chess piece
    protected abstract void placeThePieces(char[][] tempBoard);

    /**
     * Main method is for calling other methods
     * inorder to find the maximum possible
     * number of ROOKs, BISHOP, Queen and Knight
     */
    public static void main(String[] args) {
        initArguments(args);
        createChessBoard();
        initializeNoOfHoles(args);
        Chess[] chess = {new Rook(), new Bishop(), new Knight(), new Queen()};
        for (int i = 0; i < chess.length; i++) {
            System.out.println("Maximum Number of " + pieces[i] + " are " + chess[i].findMaxNoOfPiece(pieceSymbol[i]));
            printBoard();
            theAnswerBoard = theBoard;
            maxNoOfPiece = 0;  // resetting the maxNoOfPiece
        }
    }
}
