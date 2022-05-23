package week12.q1;/*
 * XCaseTwo.java
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
public class XCaseTwo extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public XCaseTwo(int id)	{
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
            new XCaseTwo(1).start();
            new XCaseTwo(2).start();
//            sleepABit(10);
            sleepABit(10);
        }


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
        new XCaseTwo(0).start();
    }
}
// here we are adding sleep statement after creation of both the objects. Hence, here
// all the threads are associated with same object but time of sleep makes all the difference.
// For instance, if we have smaller time for sleep(), in that case all threads will come
// out of waiting state, but if time in increased, it might happen that threads with id 1
// and 2 will get executed completely and then thread 0 will start, and it will go in ever waiting state.
// here 1 or 2 will get chance. 0 won't.
