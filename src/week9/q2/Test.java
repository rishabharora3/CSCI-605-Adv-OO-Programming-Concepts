package week9.q2;/*
 * Test.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
import java.util.ArrayList;
import java.util.List;

/**
 * class Test which does all the things required.
 *
 * @author Vaidehikalra
 * @author Rishabh Arora
 */
public class Test {
    /**
     * sum1() adds the area of the elements provided in the
     * list.
     * @param list1  accepts arguments of type Cube or its
     *               subclasses.
     * @return       integer value of sum.
     */
    public static int sum1(List<? extends Cube> list1) {
        int sum = 0;
        for (Cube cube : list1) {
            sum = sum + cube.area();
        }
        return sum;
    }

    /**
     * sum2() adds the area of the elements provided in the
     * list.
     * @param list1  accepts arguments of type Square or its
     *               superclasses.
     * @return       integer value of sum.
     */
    public static int sum2(List<? super Square> list1) {
        int sum = 0;
        for (Object o : list1) {
            if (o instanceof Line object) {
                sum += object.area();
            } else {
                System.out.println("Object Class");
            }
        }
        return sum;
    }

    /**
     * print() the element from the list provided in the argument.
     * @param list type of list that has to be printed.
     * @param <E>  generic type which can be of any type.
     */
    public static <E> void print(List<E> list) {
        for (E e : list)
            System.out.println(e);
    }

    /**
     * The main() method creates the object of classes and adds them to the list
     * @param args  command line arguments ignored.
     */
    public static void main(String[] args) {
        // Creating objects of every kind
        Line line = new Line(3);
        Square square = new Square(5);
        Cube cube = new Cube(10);
        Cube3D cube3D = new Cube3D(30, 4);

        // Creating list object of particular type.

        /*List<Cube> cubeList = new ArrayList<>();
        List<Cube3D> cube3DList = new ArrayList<>();
        List<Square> squareList = new ArrayList<>();
        List<Line> lineList = new ArrayList<>();*/

        ListOneType<Cube> cubeList = new ListOneType<>(Cube.class);  // creating object of ListOneType
        ListOneType<Cube3D> cube3DList = new ListOneType<>(Cube3D.class);
        ListOneType<Square> squareList = new ListOneType<>(Square.class);
        ListOneType<Line> lineList = new ListOneType<>(Line.class);

        // Adding elements to the list
        cubeList.add(cube);
        cube3DList.add(cube3D);
        squareList.add(square);
        lineList.add(line);

        lineList.add(cube);//will not add any other type than line

        // Can store object of the following class: Line, Square, Cube, 3dCube;
        List<Line> listLines = new ArrayList<>();
        listLines.add(cube);
        listLines.add(cube3D);
        listLines.add(square);
        listLines.add(line);
        print(listLines);

        //  Can store object of the following class: Cube, 3dCube;
        List<Cube> listCubes = new ArrayList<>();
        listCubes.add(cube);
        listCubes.add(cube3D);
        print(listCubes);

        //Calling method sum1() with different possible arguments
        System.out.println(sum1(cubeList));
        System.out.println(sum1(cube3DList));

        // Calling method sum2() with different possible arguments
        System.out.println(sum2(squareList));
        System.out.println(sum2(lineList));
    }
}
