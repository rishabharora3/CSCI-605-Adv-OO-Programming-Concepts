package week5.q2;

/*
 * SortedStorage.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */


/**
 * This program generates a binary search tree of strings
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class SortedStorageSet extends SortedStorage {

    /**
     *
     * @param key
     * @return
     */
    public boolean add(String key) {
        if (key == null) {
            return false;
        } else {
            return add(key, false);
        }
    }

    /**
     *
     * @param key string which has to be deleted.
     * @return
     */
    public boolean delete(String key) {
        if (find(key)) {
            if (key == null) {
                return false;
            } else {
                root = deleteNode(root, key, false);
                return true;
            }
        } else
            return false;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Values Stored:   " + printTree(root) + "\t\n";
    }

}//SortedStorageSet