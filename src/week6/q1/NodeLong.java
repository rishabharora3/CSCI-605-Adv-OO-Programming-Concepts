package week6assignment.q1;
/* NodeLong.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
/**
 * NodeLong Class
 *
 * @author  Rishabh Arora
 * @author  Vaidehi Kalra
 *
 */
public class NodeLong extends Node {

    private Long key;

    public boolean isNull(){
        return this.key == null;
    }


    public boolean add(Comparable key){
        this.key = (Long)key;
        return true;
    }

    int compare(Comparable key){
        return Long.compare(this.key, (long)key);
    }

    NodeLong(){
    }

    Node newNode(Comparable key){
        NodeLong newNode = new NodeLong();
        newNode.add(key);
        return newNode;
    }

    public Comparable getKey(){
        return key;
    }

    void setKey(Comparable key){
        this.key = (Long) key;
    }

}
