package week13.q1;/*
 * Storage.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This class contains all the method for producing Matches and producing Match Boxes.
 * It also contains method to consume the products.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Storage {
    private static  final int MATCH_STORAGE = 90;  // maximum number of matches allowed in storage
    private static final int MATCH_BOX_STORAGE = 9;  // maximum number of match box allowed in storage
    private static int matchesCounter;  // current matches produced
    private static int matchBoxCounter;  // current match box produced
    private static int totalNumberConsumerConsumed;  // used for exit condition
    final private static Object sync = new Object();  // object used for synchronizing

    /**
     * produceMatches() : This method produces number od matches.
     * Goes on wait if maximum limit reached
     */
    void produceMatches(String name){
        while (true){
            synchronized (sync){
                if(matchBoxCounter >= 1 && matchesCounter >= 50){
                    System.out.println("Producer  Match Box and Consumer in ready queue");
                    sync.notifyAll();
                }
            if(matchesCounter < MATCH_STORAGE){
                System.out.println(name +" got the chance:");
                matchesCounter++;
                System.out.println("Produce Matches --> "+matchesCounter);
            }else
            {
                try {
                    System.out.println(name+"-->Match producer on wait:");
                        sync.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * produceMatchBoxes():- This method produces match boxes.
     * Goes on wait if maximum limit reached
     */
    void produceMatchBoxes(String name){
        while (true){
            synchronized (sync){
                if(matchBoxCounter >= 1 && matchesCounter >= 50){
                    System.out.println("Producer Match Box and Consumer in ready queue");
                    sync.notifyAll();
                }
            if(matchBoxCounter < MATCH_BOX_STORAGE){
                System.out.println(name+" got the chance:");
                matchBoxCounter++;
                System.out.println("Produce Match Boxes -->"+matchBoxCounter);
            }
            else {
                try {
                    System.out.println(name+"-->Match Box producer on wait:");
                        sync.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    /**
     * consume(): method consumes the matches and the match boxes.
     * Goes on wait if required number of products not present.
     */
    void consume(String name){
        while (true) {
            synchronized (sync){

                //exit condition
                if(totalNumberConsumerConsumed > 2){
                    System.out.println("------CONSUMER REQUIREMENT FULFILLED-----");
                    System.exit(0);
                }
                if(matchesCounter >= 50 && matchBoxCounter>= 1){
                    System.out.println(name+ " got the chance:");
                    matchesCounter-= 50;
                    matchBoxCounter -= 1;
                    totalNumberConsumerConsumed++;
                    System.out.println("Consumed " + 50 + ", matches " + 1 + " match box");
                    System.out.println("Remaining matches: " + matchesCounter + ", Remaining match boxes: " + matchBoxCounter);
                    if(matchesCounter < 50 && matchBoxCounter < 9){
                        System.out.println("Producer Box and Match in ready queue");
                        sync.notifyAll();
                    }

                }
                else {
                    try {
                        System.out.println(name+" on wait:");
                        sync.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
