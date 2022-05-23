package week7.q1;
/* Node.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * Node Class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Node {

    private Comparable key;
    protected Node left;
    protected Node right;

    /**
     * default constructor of class Node
     */
    Node() {
    }

    /**
     * newNode() function creates a new Node
     * @param key key passed
     * @return Node type object
     */
    Node newNode(Comparable key) {
        return null;
    }

    /**
     * add() functions adds the node
     * @param key key to be added
     * @return a boolean value. True, if node is added.
     */
    public boolean add(Comparable key) {
        this.key = key;
        return true;
    }


    /**
     * checks if key is null.
     * @return a boolean value. True, if node is null.
     */
    public boolean isNull() {
        return this.key == null;
    }

    /**
     * getKey() gets the key
     * @return the key of Comparable type
     */
    public Comparable getKey() {
        return key;
    }

    /**
     * serKey() sets the key.
     * @param key key to be set
     */
    void setKey(Comparable key) {
        this.key = key;
    }
}