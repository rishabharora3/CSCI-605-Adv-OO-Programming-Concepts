package week7.q1;
/* SortedStorageSetWithNulls.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * Child class that manages the different data types user want to add to the
 * binary tree
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class SortedStorageSetWithNulls extends BinaryTree {

    private Ant ant = new Ant(); // creating the object of Ant type.
    private Fish fish = new Fish();  // creating the object of Fish type.
    private Mouse mouse = new Mouse();  // creating the object of Mouse type.
    private LivingThing livingThing = new LivingThing();  // creating the object of Living Thing type.

    private int noOfNulls = 0;

    /**
     * adding node of diff types
     *
     * @param key key to be added
     * @return boolean value. True, if added successfully.
     */
    public boolean add(Comparable key) {
        if (key == null) {
            noOfNulls += 1;
            return true;
        } else if (key instanceof LivingThing) {
            if (key instanceof Ant) {
                return addNode(ant, key);
            } else if (key instanceof Fish) {
                return addNode(fish, key);
            } else if (key instanceof Mouse) {
                return addNode(mouse, key);
            } else {
                return addNode(livingThing, key);
            }
        }
        return false;
    }

    /**
     * deleting node of diff types
     *
     * @param key node to be deleted with key
     * @return boolean value. True, if deleted successfully.
     */
    public boolean delete(Comparable key) {
        if (key == null && noOfNulls > 0) {
            noOfNulls -= 1;
            return true;
        } else if (key instanceof LivingThing) {
            if (key instanceof Ant) {
                ant = (Ant) deleteNode(ant, key);
                return true;
            } else if (key instanceof Fish) {
                fish = (Fish) deleteNode(fish, key);
                return true;
            } else if (key instanceof Mouse) {
                mouse = (Mouse) deleteNode(mouse, key);
                return true;
            } else {
                livingThing = (LivingThing) deleteNode(livingThing, key);
                return true;
            }
        }
        return false;
    }

    /**
     * finding nodes of diff data types
     *
     * @param key node to be found with tht key
     * @return  boolean value. True, if deleted successfully.
     */
    public boolean find(Comparable key) {
        if (key == null && noOfNulls > 0) {
            return true;
        } else if (key instanceof LivingThing) {
            if (key instanceof Ant) {
                return searchNode(ant, key) != null;
            } else if (key instanceof Fish) {
                return searchNode(fish, key) != null;
            } else if (key instanceof Mouse) {
                return searchNode(mouse, key) != null;
            } else {
                return searchNode(livingThing, key) != null;
            }
        }
        return false;
    }

    /**
     * toString() method is overridden from object class
     * @return String type of object.
     */
    @Override
    public String toString() {
        String output = "\nAnt Values Stored -   " + inorder(ant) + "\t\n";
        output += "Fish Values Stored -   " + inorder(fish) + "\t\n";
        output += "Mouse Values Stored -   " + inorder(mouse) + "\t\n";
        output += "Living Thing Values Stored -   " + inorder(livingThing) + "\t\n";
        output += "number of Nulls are -  " + noOfNulls;
        output += "\n===================================\n";
        return output;
    }

    /**
     * printing tree
     *
     * @param pointer initial root
     * @return returns the string output of inorder traversal.
     */
    public String inorder(Node pointer) {
        if (pointer == null)
            return null;

        return "\t( l: " + (pointer.left == null ? "null" : inorder(pointer.left))
                + " " + pointer.getKey() + "/ r: " +
                (pointer.right == null ? "null" : inorder(pointer.right)) + " )";
    }
}
