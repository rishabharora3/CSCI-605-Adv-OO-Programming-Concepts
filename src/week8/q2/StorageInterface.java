package week8.q2;/*
 * StorageInterface.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * Interface providing key function definitions for SortedStorageSetWithNulls class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public interface StorageInterface<T extends Comparable<T>> {
    boolean add(T x);               // true if it was successfully added, false otherwise

    boolean find(T x);           // true if x could be found, false otherwise

    boolean includesNull();      // true, if the storage include a null element, false otherwise

    boolean delete(T x);         // true if it was successfully deleted, false otherwise
}
