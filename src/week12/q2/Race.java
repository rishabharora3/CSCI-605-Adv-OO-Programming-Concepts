package week12.q2;/* Race.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * This program implements a simulation of a relay race.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class Race extends Thread {
    private static int race = 0;
    private final int runnerCount;

    /**
     * constructor
     *
     * @param runnerCount total runners in race
     */
    public Race(int runnerCount) {
        Race.race += 1;
        this.runnerCount = runnerCount;
    }

    /**
     * thread run method
     */
    @Override
    public void run() {
        startRace();
    }

    /**
     * start runner class thread
     *
     * @param index index
     * @return runner
     */
    private Runner startRunnerThread(int index) {
        Runner runner = new Runner(index + 1);
        runner.start();
        return runner;
    }

    /**
     * start race thread and synchronize runners
     */
    private void startRace() {
        for (int index = 0; index < runnerCount; index++) {
            Runner runner = startRunnerThread(index);
            synchronize(runner);
        }
    }

    /**
     * synchronize runners
     *
     * @param runner runner
     */
    private void synchronize(Runner runner) {
        synchronized (runner) {
            runner.notify();
            waitRunner(runner);
        }
    }

    /**
     * wait on runner object
     *
     * @param runner runner object
     */
    private void waitRunner(Runner runner) {
        try {
            runner.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runner inner class
     */
    public static class Runner extends Thread {
        private final int runner;

        public Runner(int runner) {
            this.runner = runner;
        }

        @Override
        public void run() {
            printOutput();
        }

        /**
         * prints desired output after each runner is handed the baton
         */
        private void printOutput() {
            if (runner != 1) {
                System.out.println("\t\tbaton is handed to runner " + runner);
            } else {
                System.out.println("New Race (race# = " + Race.race + ") is handed to runner 1");
            }
        }
    }
}//Race