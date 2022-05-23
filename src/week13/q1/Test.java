package week13.q1;/*
 * Test.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This program is the test environment for the required producer-consumer
 * problem. It creates 2 producers for both the type of productions and one consumer
 * for consuming both the type of products.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Test {

    /**
     * The driver function to create and execute threads of producers and consumers
     * @param args command line arguments for producers and consumers
     */
    public static void main(String[] args) {
        // producer consumer --> order of arguments
        Storage thisStorage = new Storage();  // creating object of storage class so that threads can use this object to call methods of class Storage
        Producer producer;
        Consumer consumer;
        int soManyProducers = Integer.parseInt(args[0]);
        int soManyConsumers = Integer.parseInt(args[1]);
        for (int i = 0; i < soManyProducers; i++) {
            if(i % 2 == 0){ // based on even odd numbers, we divided the job
                producer = new Producer(i+1, thisStorage,"MATCHES");

            }else {
                producer = new Producer(i+1, thisStorage,"MATCH_BOX");
            }
            producer.start();
        }
        for (int i = 0; i < soManyConsumers; i++) {
            consumer = new Consumer(i+1, thisStorage); // Consumer thread started
            consumer.start();
        }

    }
}
