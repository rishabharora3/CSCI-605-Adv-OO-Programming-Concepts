package week9.q2;/*
 * ListOneType.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
import java.util.ArrayList;

public class ListOneType<E> extends ArrayList<E> {

    private final Class<E> type;

    public ListOneType(Class<E> type) {
        this.type = type;
    }

    public Class<E> getMyType() {
        return this.type;
    }

    /**
     * overriding add() method from ArrayList
     * @param e accepts the element of any type
     * @return  returns a boolean value
     */
    @Override
    public boolean add(E e) {
        if (getMyType() != e.getClass()) {
            System.out.println("Incorrect Type");
//            throw new Exception("Incorrect Type");
            return false;
        }
        return super.add(e);
    }
}
