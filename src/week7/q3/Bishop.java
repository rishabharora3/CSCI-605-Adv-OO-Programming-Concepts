package week7.q3;/*
 * BISHOP.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * BISHOP is responsible for finding maximum number of possible positions around
 * the board for BISHOPs.
 *
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 */
class Bishop implements ChessPiece, ChessPieceConstants {


    private final ChessUtil chessUtil;

    public Bishop(ChessUtil chessUtil) {
        this.chessUtil = chessUtil;
    }

    /**
     * checkIfSafe method is a for validating the position of BISHOP in 2D space.
     *
     * @param row          represents the number of rows in the chess board
     * @param col          represents the number of columns in the chess board
     * @param theTempBoard is the chess board
     * @param maxRows
     * @param maxColumns
     * @return boolean value true if safe
     */
    @Override
    public boolean checkIfSafe(int row, int col, char[][] theTempBoard, int maxRows, int maxColumns) {
        for (int i = row + 1, j = col + 1; i < maxRows && j < maxColumns; i++, j++) {
            if (i != row && j != col) {
                if (!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)) {
                    return false;
                }
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (i != row && j != col) {
                if (!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)) {
                    return false;
                }
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < maxColumns; i--, j++) {
            if (i != row && j != col) {
                if (!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)) {
                    return false;
                }
            }
        }
        for (int i = row + 1, j = col - 1; i < maxRows && j >= 0; i++, j--) {
            if (i != row && j != col) {
                if (!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * placeThePieces method is a for placing position of BISHOP in 2D space.
     *
     * @param tempBoard  is the baord in the making while placing the BISHOP
     * @param maxRows
     * @param maxColumns
     */
    @Override
    public void placeThePieces(char[][] tempBoard, int maxRows, int maxColumns) {
        int sum = 0;
        for (int row = 0; row < maxRows / 2; row++) {
            for (int column = 0; column < maxColumns; column++) {
                if (checkIfSafe(row, column, tempBoard, maxRows, maxColumns)) {
                    if (tempBoard[row][column] != HOLE) {
                        tempBoard[row][column] = BISHOP;
                        ++sum;
                    }
                }
            }
        }
        for (int row = maxRows - 1; row > maxRows / 2; row--) {
            for (int column = 0; column < maxColumns; column++) {
                if (checkIfSafe(row, column, tempBoard, maxRows, maxColumns)) {
                    if (tempBoard[row][column] != HOLE) {
                        tempBoard[row][column] = BISHOP;
                        ++sum;
                    }
                }
            }
        }
        chessUtil.setAnswerBoard(tempBoard, sum);
    }


    /**
     * findMaxNoOfPiece method loops through the board to find the position
     * of the first position of the piece.
     *
     * @param piece piece that has to be placed on the board
     * @param maxRows
     * @param maxColumns
     * @param theBoard
     */
    @Override
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
}
