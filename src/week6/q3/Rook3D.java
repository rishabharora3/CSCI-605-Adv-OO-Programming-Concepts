package week6.q3;/*
 * Rook3D.java
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
public class Rook3D extends Chess3D{

    /**
     * findMaxNoOfPiece method is for placing the Rook
     * after validating position in 2D space.
     */

    static  int maxNoOfRooks = 0;
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
                            tempBoard[row][col][depth] = Rook;
                            placeThePieces(tempBoard);
                        }
                    }
                }
            }
        }
        return maxNoOfRooks;
    }

    /**
     * checkIfSafe method is a for validating the position of Rook in 2D space.
     * @param theTempBoard is the chess board
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param depth represents the number of columns in the chess board
     * @param theTempBoard is the baord in the making while placing the Rook
     */
    @Override
    protected boolean checkIfSafe(int row, int col, int depth, char[][][] theTempBoard) {
        for (int i = 0; i < maxCols; i++) {
            if(i != col){
                if((theTempBoard[row][i][depth] != Rook && theTempBoard[row][i][depth] != Queen)){
                    continue;
                }else {
                    return false;
                }
            }
        }

        //for a column, we check all the rows
        for (int j = 0; j < maxRows; j++) {
            if(j != row){
                if((theTempBoard[j][col][depth] != Rook && theTempBoard[j][col][depth] != Queen )){
                    continue;
                }else {
                    return false;
                }
            }
        }

        //for a cell, we check all the depths
        for (int k = 0; k < maxDepth; k++) {
            if(k != depth){
                if((theTempBoard[row][col][k] != Rook && theTempBoard[row][col][k] != Queen )){
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
    protected void placeThePieces(char[][][] tempBoard) {
        int sum = 0;
        for (int depth = 0; depth < maxDepth; depth++) {
        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxCols; column++) {
                if (checkIfSafe(row, column, depth,  tempBoard)) {
                    if(tempBoard[row][column][depth] != hole ){
                        tempBoard[row][column][depth] = Rook;
                        ++sum;
                    }

                    }
                }
            }
        }
        if (sum > maxNoOfRooks) {
            theAnswerBoard= createCopyAnswer(tempBoard);
            maxNoOfRooks = sum;
        }
    }
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
