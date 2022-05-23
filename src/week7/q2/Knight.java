package week7.q2;/*
 * Knight.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */
/**
 *
 * KNIGHT is responsible for finding maximum number of valid moves in
 * the board for KNIGHTs.
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 *
 */
class Knight extends Chess {
    /**
     * checkIfSafe method is a for checking the position of KNIGHT in 2D space.
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param theTempBoard is the board in the making while placing the KNIGHT
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        if(row - 2 >=0 && col - 1 >= 0){  // checking all the 8 possibilities of the movement of the knight
            if(theTempBoard[row - 2][col - 1] == KNIGHT){
                return false;
            }
        }
        if(row - 1 >=0 && col - 2 >= 0){
            if(theTempBoard[row - 1][col - 2] == KNIGHT){
                return false;
            }
        }
        if(row - 2 >=0 && col + 1 < MAX_COLUMNS){
            if(theTempBoard[row - 2][col + 1] == KNIGHT){
                return false;
            }
        }
        if(row - 1 >=0 && col + 2 < MAX_COLUMNS){
            if(theTempBoard[row - 1][col + 2] == KNIGHT){
                return false;
            }
        }
        if(row + 2 < MAX_ROWS && col + 1 < MAX_COLUMNS){
            if(theTempBoard[row + 2][col + 1] == KNIGHT){
                return false;
            }
        }
        if(row + 1 < MAX_ROWS && col + 2 < MAX_COLUMNS){
            if(theTempBoard[row + 1][col + 2] == KNIGHT){
                return false;
            }
        }
        if(row + 1 < MAX_ROWS && col - 2 >= 0){
            if(theTempBoard[row + 1][col - 2] == KNIGHT){
                return false;
            }
        }
        if(row + 2 < MAX_ROWS && col - 1 >= 0){
            return theTempBoard[row + 2][col - 1] != KNIGHT;
        }

        return true;  // if none of the places have a knight, it returns true
    }
    /**
     * placeThePieces method is a for placing position of KNIGHT in 2D space.
     * @param tempBoard is the board in the making while placing the Bishop
     */
    @Override
    protected void placeThePieces(char[][] tempBoard) {
        int sum = 0;
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != HOLE ){
                        tempBoard[row][column] = KNIGHT;
                        ++sum;
                    }
                }
            }
        }

        if (sum > maxNoOfPiece) {
            maxNoOfPiece = sum;
            theAnswerBoard = tempBoard; // assigning the board with maximum pieces to the theAnswerBoard
        }
    }
}
