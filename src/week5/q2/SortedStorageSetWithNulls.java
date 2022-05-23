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
public class SortedStorageSetWithNulls extends SortedStorage {

    public int numberOfNulls = 0;

    /**
     * @return
     */
    @Override
    public String toString() {
        return "includes so many null values = " + numberOfNulls + "\n"
                + "Values Stored:   " + inorder(root) + "\t\n";
    }

    /**
     * @param pointer
     * @return
     */
    public String inorder(Node pointer) {
        if (pointer == null)
            return null;

        return "\t( l: " + (pointer.left == null ? "null" : inorder(pointer.left)) + " " +
                pointer.value + "/ r: " + (pointer.right == null ? "null" : inorder(pointer.right)) + " )";
    }

    /**
     * @param key
     * @return
     */
    public boolean add(String key) {
        if (key == null) {
            numberOfNulls += 1;
            return true;
        } else {
            return add(key, false);
        }
    }

    /**
     * @param key string which has to be deleted.
     * @return
     */
    public boolean delete(String key) {
        if (find(key)) {
            if (key == null) {
                if (numberOfNulls > 0) {
                    numberOfNulls -= 1;
                    return true;
                } else {
                    return false;
                }
            } else {
                root = deleteNode(root, key, false);
                return true;
            }
        } else
            return false;
    }

    /**
     * @param key
     * @return
     */
    public boolean find(String key) {
        if (key == null) {
            return false;
        } else {
            return findNode(root, key) != null;
        }
    }

}//SortedStorageSetWithNulls