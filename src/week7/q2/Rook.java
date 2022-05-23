package week7.q2;/*
 * Rook.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */
/**
 *
 * Rook is responsible for finding maximum number of possible positions around
 * the board for Rooks.
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 *
 */
class Rook extends Chess {
    /**
     * checkIfSafe method is a for validating the position of Rook in 2D space.
     * @param theTempBoard is the chess board
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        //for a row, we check all the columns

        for (int i = 0; i < MAX_COLUMNS; i++) {
            if(i != col){
                if(!(theTempBoard[row][i] != ROOK && theTempBoard[row][i] != QUEEN)){
                    return false;
                }
            }
        }
        //for a column, we check all the rows
        for (int j = 0; j < MAX_ROWS; j++) {
            if(j != row){
                if(!(theTempBoard[j][col] != ROOK && theTempBoard[j][col] != QUEEN)){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * placeThePieces method is a for placing position of Rook in 2D space.
     * @param tempBoard is the baord in the making while placing the Rook
     */
    @Override
    protected void placeThePieces(char[][] tempBoard) {
        int sum = 0;
        for (int row = 0; row < MAX_ROWS; row++) {
            for (int column = 0; column < MAX_COLUMNS; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != HOLE ){
                        tempBoard[row][column] = ROOK;
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
