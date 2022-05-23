package week4.q1;
/*
 * Picture.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Picture {
    private final Vector<String> imageVector = new Vector<>();

    /**
     *  set player vector image using file
     * @param pictureFileName file name from cmd
     * @throws FileNotFoundException exception in reading file
     */
    public void setImageVector(String pictureFileName) throws FileNotFoundException {
        String currentPath = System.getProperty("user.dir") + "/";
        File file = new File(currentPath + pictureFileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            char[] characterArray = row.toCharArray();
            for (int i = 0; i < characterArray.length; i++) {
                if (i == characterArray.length - 1)
                    imageVector.add("" + characterArray[i] + "\n");
                else if (i == 0) {
                    imageVector.add("\t\t" + characterArray[i]);
                } else
                    imageVector.add("" + characterArray[i]);
            }
        }
    }

    /**
     * generates an image based on replacing n% of characters with .
     * n% refers to characterGuessed/word length * 100
     *
     * @param descLength length of the word to guess
     * @param score      characters guessed by the user
     * @return imageTempVector creating a copy of original file vector for replacing percentage
     */
    public Vector<String> generateImage(int descLength, int score) {
        Vector<String> imageTempVector = new Vector<>(imageVector);
        double percentage = (score / (double) descLength);
        int n = (int) (imageTempVector.size() - (percentage * imageTempVector.size()));
        for (int i = 0; i < n; i++) {
            int randomPos = generateRandomNumber(imageTempVector);
            if (imageTempVector.get(randomPos).contains("\n"))
                imageTempVector.set(randomPos, ".\n");
            else if (imageTempVector.get(randomPos).contains("\t\t"))
                imageTempVector.set(randomPos, "\t\t.");
            else
                imageTempVector.set(randomPos, ".");
        }
        return imageTempVector;
    }

    /**
     * generating random number to replace the image with dots(.)
     *
     * @param vec vector given to replace
     * @return randomPos random number
     */
    private static int generateRandomNumber(Vector<String> vec) {
        Random rand = new Random();
        int randomPos;
        do {
            randomPos = rand.nextInt(vec.size());
        }
        while (vec.get(randomPos).equalsIgnoreCase("."));
        return randomPos;
    }


}//Picture
