package week12.q1;/*
 * XCaseThree.java
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
public class XCaseThree extends Thread    {
    static Object o = new Object();
    static int counter = 0;
    int id;
    public XCaseThree(int id)	{
        this.id = id;
        o       = new Object();
    }

    public void run () {
        if ( id == 0 )	{
            new XCaseThree(1).start();
            new XCaseThree(2).start();
        }
        if (id == 2){
            try{
                sleep(100);
            }catch (InterruptedException e){}
        }
//        if (id == 1){
//            try{
//                sleep(100);
//            }catch (InterruptedException e){}
//        }
//        if (id == 0){
//            try{
//                sleep(100);
//            }catch (InterruptedException e){}
//        }
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
        new XCaseThree(0).start();
    }
}// In this case 1 will always go to waiting state because it gets chance to execute synchronize block after
// both the threads get chance to execute and hence there is no thread remaining to notify the thread with
// id = 1.
//
