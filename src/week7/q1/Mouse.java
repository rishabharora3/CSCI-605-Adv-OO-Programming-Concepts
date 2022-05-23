package week7.q1;/*
 * Mouse.java
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * Mouse Class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Mouse extends LivingThing {

    private String name;
    private int height;
    private float weight;

    /**
     * default constructor
     */
    public Mouse() {

    }

    /**
     * parametrized constructor
     * @param name to initialize the name
     * @param height to initialize the height
     * @param weight to initialize weight of fish
     */
    public Mouse(String name, int height, float weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    /**
     * compareTo method is overridden to compare the fi,ns
     * and the weight.
     * @param mouse the type of living thing to be considered
     * @return the int 1 if more, -1 if less, o if equal
     */
    @Override
    public int compareTo(Comparable mouse) {
        Mouse mouseKey = (Mouse) mouse;
        if (this.height > mouseKey.height && this.weight > mouseKey.weight) {
            return 1;
        } else if (this.height < mouseKey.height && this.weight < mouseKey.weight) {
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
        Mouse newNode = new Mouse();
        newNode.add(key);
        return newNode;
    }

    /**
     * toString() method is overridden from object class
     * @return String type of object.
     */
    @Override
    public String toString() {
        return this.name + "|height-" + this.height + "|weight-" + this.weight;
    }
}
