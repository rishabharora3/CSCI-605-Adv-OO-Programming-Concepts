package week6.q3;/*
 * Queen3D.java
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
public class Queen3D extends Chess3D{
    static  int maxNoOfQueens = 0;

    /**
     * findMaxNoOfPiece method is for placing the Queen
     * after validating position in 2D space.
     */
    @Override
    protected int findMaxNoOfPiece() {
        for (int depth = 0; depth < maxDepth; depth++) {
            for(int row = 0; row < maxRows; row++){
                for (int col = 0; col < maxRows; col++) {
                    if(theBoard[row][col][depth] == hole){
                        continue;
                    }else{
                        if(checkIfSafe(row, col,depth,theBoard)){
                            char[][][] tempBoard= createCopy();
                            tempBoard[row][col][depth] = Queen;
                            placeThePieces(tempBoard);
                        }
                    }
                }
            }
        }
        return maxNoOfQueens;
    }

    /**
     * checkIfSafe method is for placing the Queen
     * after validating position in 3D space.
     *
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param depth represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Rook
     * @return       a boolean value, true if safe
     */
    @Override
    protected boolean checkIfSafe(int row, int col, int depth, char[][][] theTempBoard) {
        if(checkForBishop(row, col, depth, theTempBoard) && checkForRook(row, col, depth, theTempBoard)){
            return true;
        }
        return false;
    }

    /**
     * checkForRook method is a for validating the position of Rook in 2D space.
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param depth represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Bishop
     */
    private boolean checkForRook(int row, int col, int depth, char[][][] theTempBoard){
        Rook3D rook = new Rook3D();
        if(rook.checkIfSafe(row, col, depth, theTempBoard)){
            return true;
        }
        return false;
    }
    /**
     * checkForBishop method is a for validating the position of Rook in 2D space.
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param depth represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Bishop
     */
    private boolean checkForBishop(int row, int col, int depth, char[][][] theTempBoard){
        Bishop3D bishop = new Bishop3D();
        if(bishop.checkIfSafe(row, col, depth, theTempBoard)){
            return true;
        }
        return false;
    }

    /**
     * placeThePieces method is a for placing position of Queen in 2D space.
     * @param tempBoard is the baord in the making while placing the Queen
     */
    @Override
    protected void placeThePieces(char[][][] tempBoard) {
        int sum = 0;
        for (int depth = 0; depth < maxDepth; depth++) {
            for (int row = 0; row < maxRows; row++) {
                for (int column = 0; column < maxCols; column++) {
                    if (checkIfSafe(row, column, depth,  tempBoard)) {
                        if(tempBoard[row][column][depth] != hole ){
                            tempBoard[row][column][depth] = Queen;
                            ++sum;
                        }

                    }
                }
            }
        }
        if (sum > maxNoOfQueens) {
            theAnswerBoard= createCopyAnswer(tempBoard);
            maxNoOfQueens = sum;
        }
    }

    /**
     * assigns the value of maximum piece board to the answer board
     * @param tempBoard the board which is to be copied
     * @return   a copy of the board
     */
    protected char[][][] createCopyAnswer(char[][][] tempBoard) {
        char[][][] tempBoardAns = new char[theBoard.length][theBoard[0].length][];
        for (int i = 0; i < maxDepth; i++){
            for (int j = 0; j < maxCols; j++) {
                tempBoardAns[i][j] = tempBoard[i][j].clone();
            }
        }
        return tempBoardAns;
    }


}
