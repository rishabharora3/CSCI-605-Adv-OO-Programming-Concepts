package week6assignment.q1;
/* NodeInteger.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * NodeInteger Class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class NodeInteger extends Node {

    private int key;

    public boolean isNull() {
        return this.key == 0;
    }


    public boolean add(Comparable key) {
        this.key = (int) key;
        return true;
    }

    int compare(Comparable key) {
        return Integer.compare(this.key, (int) key);
    }

    NodeInteger() {
    }

    Node newNode(Comparable key) {
        NodeInteger newNode = new NodeInteger();
        newNode.add(key);
        return newNode;
    }

    public Comparable getKey() {
        return key;
    }

    void setKey(Comparable key) {
        this.key = (int) key;
    }

}
