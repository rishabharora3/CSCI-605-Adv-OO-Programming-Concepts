package week8.q2;/*
 * Human.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

import java.util.Date;

/**
 * Node type for a binary tree
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Human implements Comparable<Human> {

    private final Date dateOfBirth;
    private final String firstName;
    private final String userId;

    Human(Date dateOfBirth, String firstName, String userId) {
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.userId = userId;
    }

    /**
     * comparison using firstName and dateOfBirth
     *
     * @param human object
     */
    @Override
    public int compareTo(Human human) {
        if (this.dateOfBirth.compareTo(human.dateOfBirth) > 0 &&
                this.firstName.compareTo(human.firstName) > 0)
            return 1;
        else if (this.dateOfBirth.compareTo(human.dateOfBirth) < 0 &&
                this.firstName.compareTo(human.firstName) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * object in string
     */
    @Override
    public String toString() {
        return "Human(" + dateOfBirth + "," + firstName + "," + userId + ")";
    }
}
