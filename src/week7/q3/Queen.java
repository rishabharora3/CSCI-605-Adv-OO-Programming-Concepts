package week7.q3;/*
 * QUEEN.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * QUEEN is responsible for finding maximum number of valid moves in
 * the board for QUEENs.
 *
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 */
class Queen implements ChessPiece, ChessPieceConstants {

    private final ChessUtil chessUtil;

    public Queen(ChessUtil chessUtil) {
        this.chessUtil = chessUtil;
    }

    /**
     * checkIfSafe method is for placing the QUEEN
     * after validating position in 3D space.
     *
     * @param row          represents the number of rows in the chess board
     * @param col          represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Rook
     * @param maxRows
     * @param maxColumns
     * @return a boolean value, true if safe
     */
    @Override
    public boolean checkIfSafe(int row, int col, char[][] theTempBoard, int maxRows, int maxColumns) {
        return checkForBishop(row, col, theTempBoard, maxRows, maxColumns) && checkForRook(row, col, theTempBoard, maxRows, maxColumns);
    }

    /**
     * placeThePieces method is a for placing position of QUEEN in 2D space.
     *
     * @param tempBoard  is the baord in the making while placing the QUEEN
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
                        tempBoard[row][column] = QUEEN;
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

    /**
     * checkForRook method is a for validating the position of Rook in 2D space.
     *
     * @param row          represents the number of rows in the chess board
     * @param col          represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Bishop
     * @param maxRows
     * @param maxColumns
     */
    private boolean checkForRook(int row, int col, char[][] theTempBoard, int maxRows, int maxColumns) {
        Rook rook = new Rook(chessUtil);
        return rook.checkIfSafe(row, col, theTempBoard, maxRows, maxColumns);
    }

    /**
     * checkForBishop method is a for validating the position of Bishop in 2D space.
     *
     * @param row          represents the rows in the chess board
     * @param col          represents the columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Bishop
     * @param maxRows
     * @param maxColumns
     */
    private boolean checkForBishop(int row, int col, char[][] theTempBoard, int maxRows, int maxColumns) {
        Bishop bishop = new Bishop(chessUtil);
        return bishop.checkIfSafe(row, col, theTempBoard, maxRows, maxColumns);
    }
}
