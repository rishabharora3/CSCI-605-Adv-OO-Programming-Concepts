package week7.q2;/*
 * Queen.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */
/**
 *
 * QUEEN is responsible for finding maximum number of valid moves in
 * the board for Queens.
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 *
 */
class Queen extends Chess {
    /**
     * checkIfSafe method is for placing the QUEEN
     * after validating position in 3D space.
     *
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param theTempBoard is the board in the making while placing the Rook
     * @return       a boolean value, true if safe
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        return checkForBishop(row, col, theTempBoard) && checkForRook(row, col, theTempBoard);
    }
    /**
     * placeThePieces method is a for placing position of QUEEN in 2D space.
     * @param tempBoard is the board in the making while placing the QUEEN
     */
    @Override
    protected void placeThePieces(char[][] tempBoard) {
        int sum = 0;
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != HOLE ){
                        tempBoard[row][column] = QUEEN;
                        ++sum;
                    }
                }
            }
        }
        if (sum > maxNoOfPiece) {
            maxNoOfPiece = sum;
            theAnswerBoard = tempBoard;  // assigning the board with maximum pieces to the theAnswerBoard
        }
    }
    /**
     * checkForRook method is a for validating the position of Rook in 2D space.
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param theTempBoard is the board in the making while placing the Bishop
     */
    private boolean checkForRook(int row, int col, char[][] theTempBoard){
        Rook rook = new Rook();
        return rook.checkIfSafe(row, col, theTempBoard);
    }
    /**
     * checkForBishop method is a for validating the position of Bishop in 2D space.
     * @param row represents the rows in the chess board
     * @param col represents the columns in the chess board
     * @param theTempBoard is the board in the making while placing the Bishop
     */
    private boolean checkForBishop(int row, int col, char[][] theTempBoard){
        Bishop bishop = new Bishop();
        return bishop.checkIfSafe(row, col, theTempBoard);
    }
}
