package week7.q3;
/*
 * ChessPieceConstants.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * ChessPieceConstants Interface is responsible for defining constants
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public interface ChessPieceConstants {
    char HOLE = 'H';
    char ROOK = 'R';
    char BISHOP = 'B';
    char KNIGHT = 'N';
    char QUEEN = 'Q';
    String[] pieces = {"Rooks", "Bishops", "Knights", "Queens"};
    char[] pieceSymbol = {'R', 'B', 'N', 'Q'};
}
