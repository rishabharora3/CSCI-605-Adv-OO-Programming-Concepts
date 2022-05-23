package week6assignment.q1;
/* NodeString.java
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
public class NodeString extends Node {

    private String key;

    public boolean isNull() {
        return this.key == null;
    }


    public boolean add(Comparable key) {
        this.key = (String) key;
        return true;
    }

    int compare(Comparable key) {
//        return Integer.compare(this.key, (String)key);
        return this.key.compareTo((String) key);
    }

    NodeString() {
    }

    Node newNode(Comparable key) {
        NodeString newNode = new NodeString();
        newNode.add(key);
        return newNode;
    }

    public Comparable getKey() {
        return key;
    }

    void setKey(Comparable key) {
        this.key = (String) key;
    }

}
