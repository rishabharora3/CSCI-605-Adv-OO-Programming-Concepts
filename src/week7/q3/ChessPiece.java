package week7.q3;
/*
 * ChessPiece.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * ChessPiece Interface is responsible for implementing methods in classes like Bishop, Queen, Knight, and Rook
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public interface ChessPiece {
    // checkIfSafe is an abstract method to validate all the moves
    boolean checkIfSafe(int row, int col, char[][] theTempBoard, int maxRows, int maxColumns);

    // placeThePieces is an abstract method to place the given chess piece
    void placeThePieces(char[][] tempBoard, int maxRows, int maxColumns);

    void findMaxNoOfPiece(char piece, int maxRows, int maxColumns, char[][] theBoard);
}
