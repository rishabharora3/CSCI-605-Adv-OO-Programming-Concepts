package week8.q2;/*
 * Address.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * Node type for a binary tree
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Address implements Comparable<Address> {

    private final int houseNumber;
    private final String streetName;
    private final String nameOfTown;
    private final String state;
    private final int zipCode;

    /**
     * constructor
     */
    Address(int houseNumber, String streetName, String nameOfTown, String state, int zipCode) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.nameOfTown = nameOfTown;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * comparison using houseNumber and streetName
     */
    @Override
    public int compareTo(Address address) {
        if (this.houseNumber > address.houseNumber &&
                this.streetName.compareTo(address.streetName) > 0)
            return 1;
        else if (this.houseNumber < address.houseNumber &&
                this.streetName.compareTo(address.streetName) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * object in string using keys
     */
    @Override
    public String toString() {
        return "Address(" + houseNumber + "," + streetName + "," +
                nameOfTown + "," + state + "," + zipCode + ")";
    }
}
