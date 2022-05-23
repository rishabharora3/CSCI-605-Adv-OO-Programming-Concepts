package week7.q2;/*
 * Bishop.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */
/**
 *
 * BISHOP is responsible for finding maximum number of possible positions around
 * the board for Bishops.
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 *
 */
class Bishop extends Chess {
    /**
     * checkIfSafe method is a for validating the position of BISHOP in 2D space.
     * @param theTempBoard is the chess board
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @return boolean value true if safe
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        for(int i =row + 1, j= col + 1; i< MAX_ROWS && j < MAX_COLUMNS; i++, j++ ){
            if(i != row && j != col){
                if(!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)){
                    return false;
                }
            }
        }
        for(int i =row - 1, j= col - 1; i >= 0 && j >=0; i--, j-- ){
            if(i != row && j != col){
                if(!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)){
                    return false;
                }
            }
        }
        for(int i =row - 1, j= col + 1; i >= 0 && j < MAX_COLUMNS; i--, j++ ){
            if(i != row && j != col){
                if(!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)){
                    return false;
                }
            }
        }
        for(int i =row + 1, j= col - 1; i< MAX_ROWS && j >= 0; i++, j-- ){
            if(i != row && j != col){
                if(!(theTempBoard[i][j] != BISHOP && theTempBoard[i][j] != QUEEN)){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * placeThePieces method is a for placing position of BISHOP in 2D space.
     * @param tempBoard is the baord in the making while placing the BISHOP
     */
    @Override
    protected void placeThePieces(char[][] tempBoard) {
        int sum = 0;
        for (int row = 0; row < MAX_ROWS / 2; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != HOLE ){
                        tempBoard[row][column] = BISHOP;
                        ++sum;
                    }
                }
            }
        }
        for (int row = MAX_ROWS - 1; row > MAX_ROWS/2; row--) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != HOLE ){
                        tempBoard[row][column] = BISHOP;
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
}
