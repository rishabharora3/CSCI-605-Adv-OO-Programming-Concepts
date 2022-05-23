package week7.q1;/*
 * Fish.java
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * Fish Class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Fish extends LivingThing {

    private String name;
    private int fins;
    private float weight;

    /**
     * default constructor
     */
    public Fish() {
    }

    /**
     * parametrized constructor
     * @param name to initialize the name
     * @param fins to initialize the fins
     * @param weight to initialize weight of fish
     */
    public Fish(String name, int fins, float weight) {
        this.name = name;
        this.fins = fins;
        this.weight = weight;
    }

    /**
     * compareTo method is overridden to compare the fi,ns
     * and the weight.
     * @param fish the type of living thing to be considered
     * @return the int 1 if more, -1 if less, o if equal
     */
    @Override
    public int compareTo(Comparable fish) {
        Fish fishKey = (Fish) fish;
        if (this.fins > fishKey.fins && this.weight > fishKey.weight) {
            return 1;
        } else if (this.fins < fishKey.fins && this.weight < fishKey.weight) {
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
        Fish newNode = new Fish();
        newNode.add(key);
        return newNode;
    }

    /**
     * toString() method is overridden from object class
     * @return String type of object.
     */
    @Override
    public String toString() {
        return this.name + "|fins-" + this.fins + "|weight-" + this.weight;
    }
}
