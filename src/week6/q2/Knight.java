/*
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
 * Knight is responsible for finding maximum number of valid moves in
 * the board for Knights.
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 *
 */
class Knight extends Chess {
    static  int maxNoOFKnights = 0;

    /**
     * findMaxNoOfPiece method is for placing the Knight
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
                        tempBoard[row][col] = Knight;
                        placeThePieces(tempBoard);
                    }
                }
            }
        }
        return maxNoOFKnights;
    }
    /**
     * checkIfSafe method is a for checking the position of Knight in 2D space.
     * @param theTempBoard is the chess board
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param theTempBoard is the board in the making while placing the Knight
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        boolean flag= false;
        if((row + col) % 2 == 0){
            flag = true;
        }
        for(int i = 0; i< maxRows; i++){
            for (int j = 0; j < maxCols; j++) {
                if((row + col) % 2 == 0){
                    return  true;
                }
            }
        }
        return false;
    }
    /**
     * placeThePieces method is a for placing position of Knight in 2D space.
     * @param tempBoard is the board in the making while placing the Bishop
     */
    @Override
    protected void placeThePieces(char[][] tempBoard) {
        int sum = 0;
        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxCols; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != hole ){
                        tempBoard[row][column] = Knight;
                        ++sum;
                    }

                }
            }
        }

        if (sum > maxNoOFKnights) {
            maxNoOFKnights = sum;
            theAnswerBoard = tempBoard;
        }
    }
}
