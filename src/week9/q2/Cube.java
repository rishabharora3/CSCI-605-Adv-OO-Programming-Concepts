package week9.q2;/*
 * Cube.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This class is the parent class of Cube3D.
 * It has an area() function which is overriden from
 * parent class. It will be overridden in subclasses.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Cube extends Square {


    Cube(int lengthOfCube) {
        super(lengthOfCube);
    }

    /**
     * area() function calculates the area()
     * @return int value of area
     */
    public int area() {
        return length * length * length;
    }

    /**
     * overridden method toString() from object class
     * @return String type value.
     */
    @Override
    public String toString() {
        return "Cube{" +
                "lengthOfCube= " + length +
                ", area= " + area() +
                '}';
    }
}
