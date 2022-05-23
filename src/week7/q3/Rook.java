package week7.q3;/*
 * Rook.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * Rook is responsible for finding maximum number of possible positions around
 * the board for Rooks.
 *
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 */
class Rook implements ChessPiece, ChessPieceConstants {

    private final ChessUtil chessUtil;

    public Rook(ChessUtil chessUtil) {
        this.chessUtil = chessUtil;
    }


    /**
     * findMaxNoOfPiece method loops through the board to find the position
     * of the first position of the piece.
     *
     * @param piece      piece that has to be placed on the board
     * @param maxRows
     * @param maxColumns
     * @param theBoard
     */
    public void findMaxNoOfPiece(char piece, int maxRows, int maxColumns, char[][] theBoard) {
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxColumns; col++) {
                if (!(theBoard[row][col] == HOLE)) {
                    if (checkIfSafe(row, col, theBoard, maxRows, maxColumns)) {
                        char[][] tempBoard = chessUtil.createCopy();
                        tempBoard[row][col] = piece;
                        placeThePieces(tempBoard, maxRows, maxColumns);
                    }
                }
            }
        }
    }

    /**
     * checkIfSafe method is a for validating the position of Rook in 2D space.
     *
     * @param theTempBoard is the chess board
     * @param row          represents the number of rows in the chess board
     * @param col          represents the number of columns in the chess board
     * @param maxRows
     * @param maxColumns
     */
    @Override
    public boolean checkIfSafe(int row, int col, char[][] theTempBoard, int maxRows, int maxColumns) {
        //for a row, we check all the columns

        for (int i = 0; i < maxColumns; i++) {
            if (i != col) {
                if (!(theTempBoard[row][i] != ROOK && theTempBoard[row][i] != QUEEN)) {
                    return false;
                }
            }
        }
        //for a column, we check all the rows
        for (int j = 0; j < maxRows; j++) {
            if (j != row) {
                if (!(theTempBoard[j][col] != ROOK && theTempBoard[j][col] != QUEEN)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * placeThePieces method is a for placing position of Rook in 2D space.
     *
     * @param tempBoard  is the baord in the making while placing the Rook
     * @param maxRows
     * @param maxColumns
     */
    @Override
    public void placeThePieces(char[][] tempBoard, int maxRows, int maxColumns) {
        int sum = 0;
        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxColumns; column++) {
                if (checkIfSafe(row, column, tempBoard, maxRows, maxColumns)) {
                    if (tempBoard[row][column] != HOLE) {
                        tempBoard[row][column] = ROOK;
                        ++sum;
                    }
                }
            }
        }
        chessUtil.setAnswerBoard(tempBoard, sum);
    }
}
