package week12.q2;/* RelayRace.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import week12assignment.q2.vk.ff.Race;

/**
 * This program implements a simulation of a relay race.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class RelayRace {

    private static int runners = 0; // runners in the race
    private static int races = 0; // number of races to run

    /**
     * gets arguments
     *
     * @param args arguments
     */
    private static void getArguments(String[] args) {
        if (args.length == 2) {
            runners = Integer.parseInt(args[0]);
            races = Integer.parseInt(args[1]);
        } else {
            System.err.println("Please pass correct arguments");
            System.exit(1);
        }
    }

    /**
     * creates a race class thread and starts
     */
    private static void startRaces() {
        week12assignment.q2.vk.ff.Race[] race = new week12assignment.q2.vk.ff.Race[races];
        for (int index = 0; index < races; index++) {
            startRaceThread(race, index);
            synchronizeRace(race[index]);
        }
    }

    /**
     * starts race thread
     *
     * @param race  race objects array
     * @param index index
     */
    private static void startRaceThread(week12assignment.q2.vk.ff.Race[] race, int index) {
        race[index] = new week12assignment.q2.vk.ff.Race(runners);
        race[index].start();
    }

    /**
     * synchronize races
     *
     * @param race single race object
     */
    private static void synchronizeRace(week12assignment.q2.vk.ff.Race race) {
        synchronized (race) {
            race.notify();
            waitRace(race);
        }
    }

    /**
     * waiting to complete rest of races
     *
     * @param race single race object
     */
    private static void waitRace(Race race) {
        try {
            race.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * driver function
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        getArguments(args);
        startRaces();
    }

}//RelayRace