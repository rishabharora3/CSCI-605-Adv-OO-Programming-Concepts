package week6.q3;/*
 * Chess3D.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This program prints a board to determine the maximum number of rooks,
 * bishops, knights and rooks we can put on the not traditional nxnxn
 * chess board, so such no piece is not in check. The chess board has
 * squares on it on which a piece can not be place.
 * You can only put a piece on the squares white or black square.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public abstract class Chess3D {
    static int maxRows; // max rows
    static int maxCols; // max columns
    static int maxDepth; // max depth
    static int noOfHoles = 0; // no of holes
    static int[] xHole = new int[10];
    static int[] yHole = new int[10];
    static int[] zHole = new int[10];
    static char[] x;
    static  char Rook = 'R';
    static  char Bishop = 'B';
    static  char Knight = 'N';
    static  char Queen = 'Q';
    static char hole = 'H';
    static char[][][] theBoard ;
    static char[][][] theAnswerBoard;
    static String[] pieces = {"Rooks", "Bishops", "Knights", "Queens"};

    /**
     * The initArguments function initializes the static variables
     *
     * @param       args        arguments which were passed
     *                          in command line.
     */

    public static void initArguments(String[] args){
        for(int index = 0; index < args.length; index++){
            if(args[index].equals("-n")){
                maxRows = maxCols = maxDepth = Integer.parseInt(args[index + 1]);
            }else if(args[index].equals("-h")){
                x = args[index + 1].toCharArray();
                xHole[noOfHoles] = Character.getNumericValue(x[0] - 1);
                yHole[noOfHoles] = Character.getNumericValue(x[2] - 1);
                zHole[noOfHoles] = Character.getNumericValue(x[4] - 1);
                noOfHoles++;
            }
        }
    }

    /**
     *the createMatrix does the initialization of board
     */
    public static void createMatrix(){
        theBoard = new char[maxRows +1][maxCols +1][maxDepth +1];
        theAnswerBoard = new char[maxRows +1][maxCols +1][maxDepth +1];
    }

    /**
     * this createHole initializes hole creation
     */
    public static void createHole(){
        for (int j = 0; j < noOfHoles; j++){
            theBoard[xHole[j]][yHole[j]][zHole[j]] = hole;
            theAnswerBoard[xHole[j]][yHole[j]][zHole[j]] = hole;
        }

    }

    /**
     * this createChessBoard creates the board with holes
     */
    public static void createChessBoard(){
        for (int depth = 0; depth < maxDepth; depth++) {
            for (int row = 0; row < maxRows +1; row++) {
                for (int col = 0; col < maxCols + 1; col++) {
                    theBoard[row][col][depth] = whatColor(row, col, depth);
                    theAnswerBoard[row][col][depth] = whatColor(row, col, depth);
                }
            }
        }

    }

    /**
     * @param row row given
     * @param col column given
     * @return char if even provides white and black if odd
     */
    private static char whatColor(int row, int col, int depth){
        if((row + col + depth) % 2 == 0){
            return 'w';
        }else{
            return 'b';
        }
    }

    /**
     * Prints the board
     */
    private static void printBoard(){
        for (int depth = 0; depth < maxDepth; depth++) {
            System.out.println("level " + depth);
            for (int row = 0; row < maxRows; row++) {
                for (int col = 0; col < maxCols; col++) {
                    System.out.print(" " + theAnswerBoard[row][col][depth]);
                }
                System.out.println();
            }
        }

    }

    /**
     * Creates a clone of the original matrix
     * @return a copy of original board
     */
    protected char[][][] createCopy() {
        char[][][] tempBoard = new char[theBoard.length][theBoard[0].length][];
        for (int i = 0; i < maxDepth; i++){
            for (int j = 0; j < maxCols; j++) {
                tempBoard[i][j] = theBoard[i][j].clone();
            }
        }
        return tempBoard;
    }


    /**
     * It's an abstract method which drives all the algorithm to find maximum no of piece
     */
    protected abstract int findMaxNoOfPiece();
    /**
     * It's an abstract method which checks the conditions for a piece at a particular cell
     *
     * @return      a boolean value if the piece is safe
     */
    protected abstract boolean checkIfSafe(int row, int col, int depth, char[][][] theTempBoard);
    /**
     * It's an abstract method which places all the other pieces wrt a piece
     *
     */
    protected abstract void placeThePieces(char[][][] tempBoard);

    /**
     * The main program which calls the method to find max kings matrix.
     *
     * @param       args        command line arguments (ignored)
     */
    public static void main(String[] args){
        initArguments(args);
        createMatrix();
        createChessBoard();
        createHole();
        Chess3D[] chess3D = {new Rook3D(),new Bishop3D(), new Knight3D(), new Queen3D()};
        for (int i = 0; i < chess3D.length; i++) {
            System.out.println("Maximum Number of " +pieces[i] + " are " + chess3D[i].findMaxNoOfPiece());
            printBoard();

        }


    }
}
