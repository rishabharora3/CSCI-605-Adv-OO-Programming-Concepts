package week9.q2;/*
 * Line.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This class is the parent class of Square, Cube and Cube3D.
 * It has an area() function which will be overridden in subclasses.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Line {


    protected int length;  // length

    Line(int length) {
        this.length = length;
    }  //parameterized  constructor

    /**
     * area() function calculates the area()
     * @return int value of area
     */
    public int area() {
        return length;
    }

    /**
     * overridden method toString() from object class
     * @return String type value.
     */
    @Override
    public String toString() {
        return "Line{" +
                "length=" + length +
                ", area= " + area() +
                '}';
    }
}
