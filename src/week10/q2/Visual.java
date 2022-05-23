package week10.q2;// original from: http://rosettacode.org/wiki/Pi_set#Java
// modified for color
/*
 * Visual.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * This class analyzes the digits of pi based on even and odd
 * and adds color red and blue in a visual representation.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class Visual extends JFrame {

    private final int LENGTH_OF_SQUARE = 3;
    private BufferedImage theImage;
    private String fileName = null;

    /**
     * Constructor sets window dimensions
     */
    public Visual() {
        super("Visual");

        int LENGTH = 330;
        int LENGTH_OF_WINDOW = LENGTH * LENGTH_OF_SQUARE;
        setBounds(100, 100, LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Constructor which creates an object and sets filename
     *
     * @param fileName the name of file
     */
    public Visual(String fileName) {
        this();
        this.fileName = fileName;
    }

    /**
     * This method returns the next digit, which must be a single digit number
     * Other characters are discarded.
     *
     * @param input file input
     * @return next char
     */
    private char nextDigit(BufferedReader input) {
        char[] buf = new char[1];
        // added code here
        try {
            char number = (char) input.read();
            // Loop to make sure next character is a digit
            while (!Character.isDigit(number)) {
                number = (char) input.read();
            }
            buf[0] = number;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf[0];
    }

    private void saveImage(BufferedImage theImage) {
        String suffix = "png";
        String outputFileName = fileName == null ? "output" : fileName + "_output" + "." + suffix;
        try {
            File file = new File(outputFileName);
            ImageIO.write(theImage, suffix, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillSquare(int xOrig, int yOrig, int color) {
        for (int x = 0; x < LENGTH_OF_SQUARE; x++)
            for (int y = 0; y < LENGTH_OF_SQUARE; y++)
                theImage.setRGB(xOrig + x, yOrig + y, color);
    }

    /**
     * Processes the file based on filename ( if null then stdin otherwise file)
     * and then creates image based on digits in file
     */
    public void createImage() {
        theImage = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_RGB);
        int red = Color.RED.getRGB();        // you might like to change the colors if you like
        int blue = Color.BLUE.getRGB();        // you might like to change the colors if you like
        try (
                BufferedReader input = new BufferedReader(fileName == null ? new InputStreamReader(System.in) :
                        fileName.contains("gz") ? new InputStreamReader(
                                new GZIPInputStream(
                                        new FileInputStream(fileName))
                        ) : new FileReader(fileName))
        ) {
            for (int y = 0; y < getHeight(); y += LENGTH_OF_SQUARE) {
                for (int x = 0; x < getWidth(); x += LENGTH_OF_SQUARE) {
                    char digit = nextDigit(input);
                    fillSquare(x, y, digit % 2 == 0 ? red : blue);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        repaint();
        saveImage(theImage);
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(theImage, 0, 0, this);
    }

    public static void main(String[] args) {
        Visual aVisual = new Visual(args.length == 1 ? args[0] : null);
        aVisual.setVisible(true);
        aVisual.createImage();
    }
}