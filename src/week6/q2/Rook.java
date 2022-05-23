/*
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
    static  int maxNoOFRooks = 0;


    /**
     * findMaxNoOfPiece method is for placing the Rook
     * after validating position in 2D space.
     */
    @Override
    protected int findMaxNoOfPiece() {

        for(int row = 0; row < maxRows; row++){
            for (int col = 0; col < maxCols; col++) {
                if(theBoard[row][col] == hole){
                    continue;
                }else{
                    if(checkIfSafe(row, col, theBoard)){
                        char[][] tempBoard= createCopy();
                        tempBoard[row][col] = Rook;
                        placeThePieces(tempBoard);
                    }
                }
            }
        }
        return maxNoOFRooks;
    }
    /**
     * checkIfSafe method is a for validating the position of Rook in 2D space.
     * @param theTempBoard is the chess board
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Rook
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        //for a row, we check all the columns

        for (int i = 0; i < maxCols; i++) {
            if(i != col){
                if((theTempBoard[row][i] != Rook && theTempBoard[row][i] != Queen)){
                    continue;
                }else {
                    return false;
                }
            }
        }
        //for a column, we check all the rows
        for (int j = 0; j < maxRows; j++) {
            if(j != row){
                if((theTempBoard[j][col] != Rook && theTempBoard[j][col] != Queen)){
                    continue;
                }else {
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
        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxCols; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != hole ){
                        tempBoard[row][column] = Rook;
                        ++sum;
                    }

                }
            }
        }

        if (sum > maxNoOFRooks) {
            maxNoOFRooks = sum;
            theAnswerBoard = tempBoard;
        }
    }
}
