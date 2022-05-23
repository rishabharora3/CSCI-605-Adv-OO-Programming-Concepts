package week6assignment.q1;
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
 * @author  Rishabh Arora
 * @author  Vaidehi Kalra
 *
 */
public class Node {

    private Comparable key;
    protected Node left;
    protected Node right;

    Node() {
    }

    Node newNode(Comparable key) {
        return null;
    }

    public boolean add(Comparable key) {
        return false;
    }

    int compare(Comparable key) {
        return -1;
    }

    public boolean isNull() {
        return false;
    }

    public Comparable getKey() {
        return key;
    }

    void setKey(Comparable key) {
        this.key = key;
    }
}