package week6assignment.q1;
/* Test.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * Test Class
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Test {
    public static void testIt(Comparable[] toInsert) {

        SortedStorageSetWithNulls aStorage = new SortedStorageSetWithNulls();

        if (!aStorage.add(toInsert[0]))
            System.out.println("	add failed");
        if (aStorage.add(toInsert[1]))
            System.out.println("	add failed");
        if (!aStorage.find(toInsert[1]))
            System.out.println("	find failed");
        if (!aStorage.delete(toInsert[1]))
            System.out.println("	delete failed");
        if (aStorage.delete(toInsert[1]))
            System.out.println("	delete failed");
        if (!aStorage.add(null))
            System.out.println("	add null failed");
        if (!aStorage.add(null))
            System.out.println("	add null failed");
        if (!aStorage.delete(null))
            System.out.println("	delete null failed");
        if (!aStorage.delete(null))
            System.out.println("	delete null failed");
        if (aStorage.delete(null))
            System.out.println("	delete null failed");
        if (!aStorage.toString().equals("null"))
            System.out.println("	tree should be empty");
        System.out.println(" Traversal" + aStorage);
    }

    public static void main(String args[]) {
        String strings[] = {"3", "3", "4", "2", "5", "1", null, null};
        testIt(strings);
        Long longs[] = {Long.valueOf(3), Long.valueOf(3), Long.valueOf(5), Long.valueOf(6), Long.valueOf(9)};
        testIt(longs);
        Integer ints[] = {Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(7), Integer.valueOf(2),
                null, null};
        testIt(ints);
    }
}
