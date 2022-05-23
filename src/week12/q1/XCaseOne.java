package week12.q1;/*
 * XCaseOne.java
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

public class XCaseOne extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public XCaseOne(int id)	{
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
            new XCaseOne(1).start();
            sleepABit(100);
            new XCaseOne(2).start();
        }

        synchronized ( o ) {

            try {
                System.err.println(id + " --->");
                o.notifyAll();
                o.wait();
                sleepABit(10);  // doesn't make a diffrence
                o.notifyAll();
                System.err.println(id + " <---");
            }
            catch (  InterruptedException e ) {
            }
        }
    }
    public static void main (String args []) throws InterruptedException {
        new XCaseOne(0).start();
    }
}
// id = 1, will go to waiting state and will remain in ever waiting state because the object
// that thread 1 was associated with was replaced by new object and hence it never gets notified ever.
// Also, because of the sleep() placed in run(), thread with id = 0 will never get the chance to
// go before thread with id 1. Hence, this is the only output possible.
//