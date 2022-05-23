package week12.q1;/*
 * XCaseFour.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */
/**
 * This program is case one of the required execution.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class XCaseFour extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public XCaseFour(int id)	{
        this.id = id;
        o       = new Object();
    }
    public void sleepABit(long time){
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void run () {
        if ( id == 0 )	{
            new XCaseFour(1).start();
            new XCaseFour(2).start();

        }
        sleepABit(100);
        synchronized ( o ) {

            try {
                System.err.println(id + " --->");
                o.notifyAll();
                o.wait();

                o.notifyAll();
                System.err.println(id + " <---");
            }
            catch (  InterruptedException e ) {
            }
        }
    }
    public static void main (String args []) throws InterruptedException {
        new XCaseFour(0).start();
    }
}
// this case will ensure that thread that starts first will also end last.
// because of the equal sleep time.