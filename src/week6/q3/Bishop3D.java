package week6.q3;/*
 * Bishop3D.java
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
 */public class Bishop3D extends Chess3D{
     static  int maxNoOfBishops = 0;

    /**
     * findMaxNoOfPiece method is for placing and counting the Bishop
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
                            tempBoard[row][col][depth] = Bishop;
                            placeThePieces(tempBoard);
                        }
                    }
                }
            }
        }
        return maxNoOfBishops;
    }

    /**
     * checkIfSafe method is a for validating the position of Bishop in 2D space.
     * @param theTempBoard is the chess board
     * @param row represents the number of rows in the chess board
     * @param col represents the number of columns in the chess board
     * @param depth represents the depth in the chess board
     * @return boolean value true if safe
     */
    @Override
    protected boolean checkIfSafe(int row, int col, int depth, char[][][] theTempBoard) {
        int count=0;
        for(int i =row + 1, j= col + 1; i< maxRows && j < maxCols; i++, j++ ){
            if(i != row && j != col){
                if(theTempBoard[i][j][depth] != Bishop && theTempBoard[i][j][depth] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        for(int i =row - 1, j= col - 1; i >= 0 && j >=0; i--, j-- ){
            if(i != row && j != col){
                if(theTempBoard[i][j][depth] != Bishop && theTempBoard[i][j][depth] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        for(int i =row - 1, j= col + 1; i >= 0 && j < maxCols; i--, j++ ){
            if(i != row && j != col){
                if(theTempBoard[i][j][depth] != Bishop && theTempBoard[i][j][depth] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        for(int i =row + 1, j= col - 1; i< maxRows && j >= 0; i++, j-- ){
            if(i != row && j != col){
                if(theTempBoard[i][j][depth] != Bishop && theTempBoard[i][j][depth] != Queen){
                    continue;
                }else {
                    return false;
                }
            }
        }
        for(int i = depth + 1; i < maxDepth - 1; i++ ){
            if(row - i >=0 && col - i >= 0){
                if(theTempBoard[row-i][col-i][i] == Bishop || theTempBoard[row-i][col-i][i] == Queen){
                    return false;
                }
            }
            if(row - i >=0 && col + i < maxCols){
                if(theTempBoard[row-i][col+i][i] == Bishop || theTempBoard[row-i][col+i][i] == Queen){
                    return false;
                }
            }
            if(row + i < maxRows && col - i >= 0){
                if(theTempBoard[row+i][col-i][i] == Bishop || theTempBoard[row+i][col-i][i] == Queen){
                    return false;
                }
            }
            if(row + i < maxRows && col + i < maxCols){
                if(theTempBoard[row+i][col+i][i] == Bishop || theTempBoard[row+i][col+i][i] == Queen){
                    return false;
                }
            }
        }
        for(int i = depth - 1; i >= 0; i-- ){
            ++count;
            if(row - count >=0 && col - count >= 0){
                if(theTempBoard[row-count][col-count][i] == Bishop || theTempBoard[row-count][col-count][i] == Queen){
                    return false;
                }
            }
            if(row - count >=0 && col + count < maxCols){
                if(theTempBoard[row-count][col+count][i] == Bishop || theTempBoard[row-count][col+count][i] == Queen){
                    return false;
                }
            }
            if(row + count < maxRows && col - count >= 0){
                if(theTempBoard[row+count][col-count][i] == Bishop || theTempBoard[row+count][col-count][i] == Queen){
                    return false;
                }
            }
            if(row + count < maxRows && col + count < maxCols){
                if(theTempBoard[row+ count][col+ count][i] == Bishop || theTempBoard[row+count][col+count][i] == Queen){
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
    protected void placeThePieces(char[][][] tempBoard) {
        int sum = 0;
        for (int depth = 0; depth < maxDepth; depth++) {
            for (int row = 0; row < maxRows/2; row++) {
                for (int column = 0; column < maxCols; column++) {
                    if (checkIfSafe(row, column, depth,  tempBoard)) {
                        if(tempBoard[row][column][depth] != hole ){
                            tempBoard[row][column][depth] = Bishop;
                            ++sum;
                        }
                    }
                }
            }
        }
        for (int depth = 0; depth < maxDepth; depth++) {
            for (int row = maxRows - 1; row > maxRows / 2; row--) {
                for (int column = 0; column < maxCols; column++) {

                    if (checkIfSafe(row, column, depth,  tempBoard)) {
                        if(tempBoard[row][column][depth] != hole ){
                            tempBoard[row][column][depth] = Bishop;
                            ++sum;
                        }
                    }
                }
            }
        }
        if (sum > maxNoOfBishops) {
            theAnswerBoard= createCopyAnswer(tempBoard);
            maxNoOfBishops = sum;

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
