package week7.q1;/*
 * Ant.java
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * Ant Class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Ant extends LivingThing {
    private String name;
    private int length;
    private int legs;

    /**
     * default constructor
     */
    public Ant() {

    }

    /**
     * parametrized constructor
     * @param name to initialize the name
     * @param length to initialize the length
     * @param legs to initialize the number of legs
     */
    public Ant(String name, int length, int legs) {
        this.name = name;
        this.length = length;
        this.legs = legs;
    }

    /**
     * compareTo method is overridden to compare the length
     * hands and the number of legs
     * @param ant the type of living thing to be considered
     * @return the int 1 if more, -1 if less, o if equal
     */
    @Override
    public int compareTo(Comparable ant) {
        Ant antKey = (Ant) ant;
        if (this.legs > antKey.legs && this.length > antKey.length) {
            return 1;
        } else if (this.legs < antKey.legs && this.length < antKey.length) {
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
        Ant newNode = new Ant();
        newNode.add(key);
        return newNode;
    }

    /**
     * toString() method is overridden from object class
     * @return String type of object.
     */
    @Override
    public String toString() {
        return name + "|legs-" + legs + "|length-" + legs;
    }
}
