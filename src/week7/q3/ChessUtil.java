package week7.q3;
/*
 * ChessUtil.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * ChessUtil Interface is responsible for calling Chess class functions
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public interface ChessUtil {
    void setAnswerBoard(char[][] tempBoard, int sum);

    char[][] createCopy();
}
