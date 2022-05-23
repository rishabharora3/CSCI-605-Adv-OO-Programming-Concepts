/*
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
 * Bishop is responsible for finding maximum number of possible positions around
 * the board for Bishops.
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 *
 */
class Bishop extends Chess {
    static  int maxNoOFBishops = 0;

    /**
     * findMaxNoOfPiece method is for placing and counting the Bishop
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
                        tempBoard[row][col] = Bishop;
                        placeThePieces(tempBoard);
                    }
                }
            }
        }
        return maxNoOFBishops;
    }
    /**
     * checkIfSafe method is a for validating the position of Bishop in 2D space.
     * @param theTempBoard is the chess board
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @return boolean value true if safe
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        for(int i =row + 1, j= col + 1; i< maxRows && j < maxCols; i++, j++ ){
            if(i != row && j != col){
                if(theTempBoard[i][j] != Bishop && theTempBoard[i][j] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        for(int i =row - 1, j= col - 1; i >= 0 && j >=0; i--, j-- ){
            if(i != row && j != col){
                if(theTempBoard[i][j] != Bishop && theTempBoard[i][j] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        for(int i =row - 1, j= col + 1; i >= 0 && j < maxCols; i--, j++ ){
            if(i != row && j != col){
                if(theTempBoard[i][j] != Bishop && theTempBoard[i][j] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        for(int i =row + 1, j= col - 1; i< maxRows && j >= 0; i++, j-- ){
            if(i != row && j != col){
                if(theTempBoard[i][j] != Bishop && theTempBoard[i][j] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * placeThePieces method is a for placing position of Bishop in 2D space.
     * @param tempBoard is the baord in the making while placing the Bishop
     */
    @Override
    protected void placeThePieces(char[][] tempBoard) {
        int sum = 0;
        for (int row = 0; row < maxRows / 2; row++) {
            for (int column = 0; column < maxCols; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != hole ){
                        tempBoard[row][column] = Bishop;
                        ++sum;
                    }
                }
            }
        }
        for (int row = maxRows - 1; row > maxRows/2; row--) {
            for (int column = 0; column < maxCols; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != hole ){
                        tempBoard[row][column] = Bishop;
                        ++sum;
                    }
                }
            }
        }
        if (sum > maxNoOFBishops) {
            maxNoOFBishops = sum;
            theAnswerBoard = tempBoard;
        }
    }
}// -h 1.1 -h 8.8 -h 3.2
