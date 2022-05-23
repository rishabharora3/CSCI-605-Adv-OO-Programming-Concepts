package week13.q1;/*
 * Producer.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This class contains all the method for producing Matches and producing Match Boxes.
 * It contains the overridden run method() which calls the production method.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Producer extends Thread {

        private String type;
        private String m = "MATCHES";  // type of producer
        private String mP = "MATCH_BOX";
        private Storage thisStorage; // object to call the method

        Producer(int id, Storage thisStorage, String type)	{
            this.thisStorage = thisStorage;
            this.type = type;
            setName("Producer: " + id );

        }

    /**
     * run() overridden method which calls the main functional method.
     */
    public void run()	{
            if(this.type.equals(m)){
                thisStorage.produceMatches(currentThread().getName());
            }
            if(this.type.equals(mP)){
                thisStorage.produceMatchBoxes(currentThread().getName());
            }
        }

}
