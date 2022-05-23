package week6assignment.q1;
/* SortedStorageSetWithNulls.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
/**
 * Child class that manages the different data types user want to add to the
 * binary tree
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

import java.lang.Integer;

public class SortedStorageSetWithNulls extends BinaryTree {

    private NodeLong rootLong = new NodeLong(); // long node
    private NodeInteger rootInt = new NodeInteger(); // int node
    private NodeString rootString = new NodeString(); // string node object
    private int noOfNulls = 0;

    /**
     * adding node of diff types
     * @param key
     * @return
     */
    public boolean add(Comparable key) {
        if (key == null) {
            noOfNulls += 1;
            return true;
        } else if (key instanceof Integer) {
            return addNode(rootInt, key);
        } else if (key instanceof String) {
            return addNode(rootString, key);
        } else if (key instanceof Long) {
            return addNode(rootLong, key);
        }
        return false;
    }

    /**
     * deleting node of diff types
     * @param key
     * @return
     */
    public boolean delete(Comparable key) {
        if (key == null && noOfNulls > 0) {
            noOfNulls -= 1;
            return true;
        } else if (key instanceof Integer) {
            rootInt = (NodeInteger) deleteNode(rootInt, key);
            return true;
        } else if (key instanceof String) {
            rootString = (NodeString) deleteNode(rootString, key);
            return true;
        } else if (key instanceof Long) {
            rootLong = (NodeLong) deleteNode(rootLong, key);
            return true;
        }
        return false;
    }

    /**
     * finding nodes of diff data types
     * @param key
     * @return
     */
    public boolean find(Comparable key) {
        if (key == null && noOfNulls > 0) {
            return true;
        } else if (key instanceof Integer) {
            return searchNode(rootInt, key) != null;
        } else if (key instanceof String) {
            return searchNode(rootString, key) != null;
        } else if (key instanceof Long) {
            return searchNode(rootLong, key) != null;
        }
        return false;
    }

    @Override
    public String toString() {
        String output = "Int Values Stored -   " + inorder(rootInt) + "\t\n";
        output += "String Values Stored -   " + inorder(rootString) + "\t\n";
        output += "Long Values Stored -   " + inorder(rootLong) + "\t\n";
        output += "number of Nulls are -  " + noOfNulls;
        return output;
    }

    /**
     * printing tree
     * @param pointer
     * @return
     */
    public String inorder(Node pointer) {
        if (pointer == null)
            return null;

        return "\t( l: " + (pointer.left == null ? "null" : inorder(pointer.left))
                + " " + pointer.getKey() + "/ r: " +
                (pointer.right == null ? "null" : inorder(pointer.right)) + " )";

    }
}
