package week6.q3;/*
 * Knight3D.java
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
 */public class Knight3D extends Chess3D{

    static  int maxNoOfKnights = 0;
    /**
     * findMaxNoOfPiece method is for placing the Knight
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
                            tempBoard[row][col][depth] = Knight;
                            placeThePieces(tempBoard);
                        }
                    }
                }
            }
        }
        return maxNoOfKnights;
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

         //loop on depth
                    if((row + col + depth) % 2 == 0){
                        return true;
                    }
                    else {
                        return false;
                    }


    }

    /**
     * placeThePieces method is a for placing position of Knight in 2D space.
     * @param tempBoard is the board in the making while placing the Bishop
     */
    @Override
    protected void placeThePieces(char[][][] tempBoard) {
        int sum = 0;
        for (int depth = 0; depth < maxDepth; depth++) {
            for (int row = 0; row < maxRows; row++) {
                for (int column = 0; column < maxCols; column++) {
                    if (checkIfSafe(row, column, depth,  tempBoard)) {
                        if(tempBoard[row][column][depth] != hole ){
                            tempBoard[row][column][depth] = Knight;
                            ++sum;
                        }
                    }
                }
            }
        }
        if (sum > maxNoOfKnights) {
            theAnswerBoard= createCopyAnswer(tempBoard);
            maxNoOfKnights = sum;

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
