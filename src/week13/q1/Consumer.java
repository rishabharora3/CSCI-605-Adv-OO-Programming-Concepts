package week13.q1;/*
 * Consumer.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This class contains all the method for consumer
 * It contains the overridden run method() which calls the consume method.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Consumer extends Thread{

    private Storage thisStorage;

    Consumer(int id, Storage thisStorage)	{
        this.thisStorage = thisStorage;
        setName("Consumer: "+ id);
    }

    /**
     * overridden run() method which calls the consume() method.
     */
    public void run(){
        thisStorage.consume(currentThread().getName());
    }



}
