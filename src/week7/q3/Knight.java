package week7.q3;/*
 * KNIGHT.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * KNIGHT is responsible for finding maximum number of valid moves in
 * the board for KNIGHTs.
 *
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 */
class Knight implements ChessPiece, ChessPieceConstants {


    private final ChessUtil chessUtil;

    public Knight(ChessUtil chessUtil) {
        this.chessUtil = chessUtil;
    }

    /**
     * checkIfSafe method is a for checking the position of KNIGHT in 2D space.
     *
     * @param row          represents the number of rows in the chess board
     * @param col          represents the number of columns in the chess board
     * @param theTempBoard is the board in the making while placing the KNIGHT
     * @param maxRows
     * @param maxColumns
     */
    @Override
    public boolean checkIfSafe(int row, int col, char[][] theTempBoard, int maxRows, int maxColumns) {
        if (row - 2 >= 0 && col - 1 >= 0) {
            if (theTempBoard[row - 2][col - 1] == KNIGHT) {
                return false;
            }
        }
        if (row - 1 >= 0 && col - 2 >= 0) {
            if (theTempBoard[row - 1][col - 2] == KNIGHT) {
                return false;
            }
        }
        if (row - 2 >= 0 && col + 1 < maxColumns) {
            if (theTempBoard[row - 2][col + 1] == KNIGHT) {
                return false;
            }
        }
        if (row - 1 >= 0 && col + 2 < maxColumns) {
            if (theTempBoard[row - 1][col + 2] == KNIGHT) {
                return false;
            }
        }
        if (row + 2 < maxRows && col + 1 < maxColumns) {
            if (theTempBoard[row + 2][col + 1] == KNIGHT) {
                return false;
            }
        }
        if (row + 1 < maxRows && col + 2 < maxColumns) {
            if (theTempBoard[row + 1][col + 2] == KNIGHT) {
                return false;
            }
        }
        if (row + 1 < maxRows && col - 2 >= 0) {
            if (theTempBoard[row + 1][col - 2] == KNIGHT) {
                return false;
            }
        }
        if (row + 2 < maxRows && col - 1 >= 0) {
            return theTempBoard[row + 2][col - 1] != KNIGHT;
        }

        return true;
    }

    /**
     * placeThePieces method is a for placing position of KNIGHT in 2D space.
     *
     * @param tempBoard  is the board in the making while placing the Bishop
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
                        tempBoard[row][column] = KNIGHT;
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
     * @param piece      piece that has to be placed on the board
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
