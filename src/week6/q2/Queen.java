/*
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
 * Queen is responsible for finding maximum number of valid moves in
 * the board for Queens.
 * @author Vaidehi Kalra
 * @author Rishabh Arora
 *
 */
class Queen extends Chess {
    static  int maxNoOFQueen = 0;

    /**
     * findMaxNoOfPiece method is for placing the Queen
     * after validating position in 3D space.
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
                        tempBoard[row][col] = Queen;
                        placeThePieces(tempBoard);
                    }
                }
            }
        }
        return maxNoOFQueen;
    }

    /**
     * checkIfSafe method is for placing the Queen
     * after validating position in 3D space.
     *
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Rook
     * @return       a boolean value, true if safe
     */
    @Override
    protected boolean checkIfSafe(int row, int col, char[][] theTempBoard) {
        if(checkForBishop(row, col, theTempBoard) && checkForRook(row, col, theTempBoard)){
            return true;
        }
        return false;
    }
    /**
     * placeThePieces method is a for placing position of Queen in 2D space.
     * @param tempBoard is the baord in the making while placing the Queen
     */
    @Override
    protected void placeThePieces(char[][] tempBoard) {
        int sum = 0;
        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxCols; column++) {
                if (checkIfSafe(row, column, tempBoard)) {
                    if(tempBoard[row][column] != hole ){
                        tempBoard[row][column] = Queen;
                        ++sum;
                    }
                }
            }
        }
        if (sum > maxNoOFQueen) {
            maxNoOFQueen = sum;
            theAnswerBoard = tempBoard;
        }
    }
    /**
     * checkForRook method is a for validating the position of Rook in 2D space.
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Bishop
     */
    private boolean checkForRook(int row, int col, char[][] theTempBoard){
        Rook rook = new Rook();
        if(rook.checkIfSafe(row, col, theTempBoard)){
            return true;
         }
        return false;
    }
    /**
     * checkForBishop method is a for validating the position of Bishop in 2D space.
     * @param row represents the rows in the chess board
     * @param col represents the columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Bishop
     */
    private boolean checkForBishop(int row, int col, char[][] theTempBoard){
        Bishop bishop = new Bishop();
        if(bishop.checkIfSafe(row, col, theTempBoard)){
            return true;
        }
        return false;
    }
}
