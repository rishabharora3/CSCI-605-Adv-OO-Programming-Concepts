package week7.q1;
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
    /**
     * testIt method runs all the operations
     * @param toInsert  what type of object has to be inserted
     */
    public static void testIt(Comparable[] toInsert) {

        SortedStorageSetWithNulls aStorage = new SortedStorageSetWithNulls();

        if (!aStorage.add(toInsert[0]))
            System.out.println("	add failed");
        if (aStorage.add(toInsert[1]))
            System.out.println("	add failed");
        if (aStorage.add(toInsert[2]))
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
        System.out.println("Traversal" + aStorage);
    }

    /**
     * The main method() creates the objects of all the classes and calls
     * teh testIt() method.
     *
     * @param args  command line arguments
     */
    public static void main(String[] args) {
        Ant[] ant = {new Ant("x", 4, 4), new Ant("y", 3, 3),
                new Ant("z", 5, 5)};
        testIt(ant);
        Fish[] fish = {new Fish("x", 4, 5), new Fish("y", 3, 2),
                new Fish("z", 6, 8)};
        testIt(fish);
        Mouse[] mouse = {new Mouse("x", 5, 5.5f), new Mouse("y", 4, 3.2f),
                new Mouse("z", 2, 2f)};
        testIt(mouse);
        LivingThing[] livingThing = {new LivingThing("x", 2, 5), new LivingThing("y", 1, 4),
                new LivingThing("z", 3, 6)};
        testIt(livingThing);
    }
}
