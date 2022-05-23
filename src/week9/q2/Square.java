package week9.q2;/*
 * Square.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This class is the parent class of Cube and Cube3D.
 * It has an area() function which is overriden from
 * parent class. It will be overridden in subclasses.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Square extends Line {


    Square(int lengthOfSide) {
        super(lengthOfSide);
    } //parameterized constructor

    /**
     * area() function calculates the area()
     * @return int value of area
     */
    public int area() {
        return length * length;
    }

    /**
     * overridden method toString() from object class
     * @return String type value.
     */
    @Override
    public String toString() {
        return "Square{" +
                "lengthOfSide=" + length +
                ", area= " + area() +
                '}';
    }
}
