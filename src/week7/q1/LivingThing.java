package week7.q1;/*
* LivingThing.java
* Version:
*     $16.0.2$
*
* Revisions:
*     $1.0$
*/

/**
 * LivingTHing Class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class LivingThing extends Node implements Comparable<Comparable> {

    private String name;
    private int hands;
    private int legs;

    /**
     * default constructor
     */
    public LivingThing() {

    }

    /**
     * parametrized constructor
     * @param name to initialize the name
     * @param hands to initialize the number of hands
     * @param legs to initialize the number of legs
     */
    public LivingThing(String name, int hands, int legs) {
        this.name = name;
        this.hands = hands;
        this.legs = legs;
    }

    /**
     * compareTo method is overridden to compare the number
     * of hands and the number of legs
     * @param livingThing the type of living thing to be considered
     * @return the int 1 if more, -1 if less, o if equal
     */
    @Override
    public int compareTo(Comparable livingThing) {
        LivingThing livingThingKey = (LivingThing) livingThing;
        if (this.hands > livingThingKey.hands && this.legs > livingThingKey.legs) {
            return 1;
        } else if (this.legs < livingThingKey.legs && this.hands < livingThingKey.hands) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * newNode() creates the node and calls the add
     * function.
     * @param key key passed
     * @return Node type of object created.
     */
    @Override
    Node newNode(Comparable key) {
        LivingThing newNode = new LivingThing();
        newNode.add(key);
        return newNode;
    }

    /**
     * toString() method is overridden from object class
     * @return String type of object.
     */
    @Override
    public String toString() {
        return this.name + "|hands-" + this.hands + "|legs-" + this.legs;
    }
}
