package week8.q2;/* Node.java
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
public class Node<T> {

    public T key;
    public Node<T> left;
    public Node<T> right;

    Node(T key) {
        this.key = key;
    }
}