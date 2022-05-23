package week8.q2;/*
 * Test.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test class for testing the Generic class which creates different type of binary trees
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Test {
    public static <T extends Comparable<T>> void testIt(T[] toInsert) {

        SortedStorageSetWithNulls<T> aStorage = new SortedStorageSetWithNulls<>();

        for (T element : toInsert) {
            if (!aStorage.add(element))
                System.out.println("	add failed: " + element);
            else
                System.out.println("	added: " + element);
        }
        System.out.println("Traversal" + aStorage);
        System.out.println("Includes Null: " + aStorage.includesNull());
        for (T element : toInsert) {
            if (!aStorage.find(element))
                System.out.println("	find failed: " + element);
            else
                System.out.println("	found: " + element);
        }
        for (T element : toInsert) {
            if (!aStorage.delete(element))
                System.out.println("	delete failed: " + element);
            else
                System.out.println("	deleted: " + element);
        }
        System.out.println("tree should be empty");
        System.out.println("Traversal" + aStorage);
        System.out.println("====================================================");
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String[] strings = {"3", "3", "4", "2", "5", "1", "1", null, null, null};
        testIt(strings);
        Integer[] ints = {3, 3, 4, 2, 5, 1, 1, null, null, null};
        testIt(ints);
        Human[] humans = {new Human(parseDate("2021-06-01"), "cc", "292929"),
                new Human(parseDate("2021-06-01"), "cc", "232335"),
                new Human(parseDate("2021-02-01"), "bb", "324243"),
                new Human(parseDate("2021-08-01"), "dd", "554545"),
                new Human(parseDate("2021-08-01"), "dd", "765656"),
                null, null, null};
        testIt(humans);
        MusicLP[] musicLPS = {new MusicLP(2023, "cc", "dsjdj", 3, 2),
                new MusicLP(2023, "cc", "dsjdj", 3, 2),
                new MusicLP(2022, "bb", "dsjdj", 2, 2),
                new MusicLP(2025, "dd", "dsjdj", 5, 2),
                new MusicLP(2025, "dd", "dsjdj", 5, 2),
                null, null, null};
        testIt(musicLPS);
        Address[] addresses = {new Address(110, "cc", "sdwsdf",
                "wa", 39393),
                new Address(110, "cc", "sdwsdf",
                        "wa", 39393),
                new Address(100, "bb", "sdwsdf",
                        "wa", 39393),
                new Address(120, "dd", "sdwsdf",
                        "wa", 39393),
                new Address(120, "dd", "sdwsdf",
                        "wa", 39393),
                null, null, null};
        testIt(addresses);
    }
}
