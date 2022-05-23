package week8.q2;/*
 * MusicLP.java
 *
 * Version:
 *     v1.0
 *
 * Revisions:
 *     $Log$
 */

/**
 * Node type for a binary tree
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */
public class MusicLP implements Comparable<MusicLP> {

    private final int year;
    private final String artist;
    private final String title;
    private final float length;
    private final int tracks;

    MusicLP(int year, String artist, String title, float length, int tracks) {
        this.year = year;
        this.artist = artist;
        this.title = title;
        this.length = length;
        this.tracks = tracks;
    }

    /**
     * comparison using year, artist and length
     *
     * @param musicLP object
     */
    @Override
    public int compareTo(MusicLP musicLP) {
        if (this.year > musicLP.year &&
                this.artist.compareTo(musicLP.artist) > 0
                && this.length > musicLP.length)
            return 1;
        else if (this.year < musicLP.year &&
                this.artist.compareTo(musicLP.artist) < 0
                && this.length < musicLP.length) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * object in string
     *
     */
    @Override
    public String toString() {
        return "MusicLP(" + year + "," + artist + "," +
                title + "," + length + "," + tracks + ")";
    }
}
