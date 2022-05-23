package week4.q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

    boolean FA1(char ch[]) {
        int state = 0;
        int failState = 4;
        for (int index = 0; (state != failState) && (index < ch.length); index++) {
            if (state == 0) {
                if (ch[index] == 'a') {
                    if (index == 0) {
                        state = 1;
                        continue;
                    } else if (ch[index - 1] == ' ') {
                        state = 1;
                        continue;
                    } else if (ch[index - 1] == 'a') {
                        state = 0;
                        continue;
                    }
                }
            }

            if (state == 1) {
                if (ch[index] == 'b') {
                    if (index == ch.length - 1) {
                        state = 2;
                        break;
                    } else if (ch[index + 1] == ' ') {
                        state = 2;
                        break;
                    } else if (ch[index + 1] == 'b' || ch[index + 1] == 'a') {
                        state = 0;
                    }
                }
            }

        }
        if (state == 2) {
            return true;
        } else
            return false;

    }

    public boolean FA2(char[] ch) {
        int state = 0;
        int failState = 4;
        for (int index = 0; (state != failState) && (index < ch.length); index++) {
            if (state == 0) {
                if (ch[index] == 'a') {
                    state = 1;
                    continue;

                }
            }

            if (state == 1) {
                if (ch[index] != 'a' && ch[index] != 'b') {
                    state = 0;
                    continue;
                } else if (ch[index] == 'a') {
                    state = 1;
                    continue;
                } else if (ch[index] == 'b' && index == ch.length - 1) {
                    state = 3;
                    break;
                } else if (ch[index] == 'b' && index != ch.length - 1) {
                    state = 2;
                    continue;
                }
            }

            if (state == 2) {
                if (ch[index] != 'b') {
                    state = 3;
                    break;
                } else state = 0;

            }

        }
        if (state == 3) {
            return true;
        } else
            return false;

    }

    public boolean FA3(char ch[]) {
        return false;
    }

    public boolean FA4(char[] ch) {
        return false;
    }

    public boolean FA5(char[] ch) {
        return false;
    }

    public boolean FA6(char ch[]) {
        return false;
    }

    public boolean FA7(char[] ch) {
        int state = 0;
        int errorState = 4;
        int finalState = 3;
        for (int index = 0; (state != errorState) && (index < ch.length); index++) {
            if (state == 0) {
                if (ch[index] == ' ') {
                    state = 0;
                } else if (ch[index] == ch[index + 1]) {
                    state = 1;
                }
            } else if (state == 1) {

            }
        }
        return false;
    }

    void checkAndDisplay(char[] characterArray) {
        String patterns[] = {"^ab$", ".a+b.", " ", " ", " ", " ", " "};
        boolean checkPatterns[] = new boolean[patterns.length];
        for (int i = 0; i < checkPatterns.length; i++) {
            checkPatterns[i] = false;
        }
        checkPatterns[0] = FA1(characterArray);
        checkPatterns[1] = FA2(characterArray);
        checkPatterns[2] = FA3(characterArray);
        checkPatterns[3] = FA4(characterArray);
        checkPatterns[4] = FA5(characterArray);
        checkPatterns[5] = FA6(characterArray);
        checkPatterns[6] = FA7(characterArray);

        for (int index = 0; index < patterns.length; index++) {
            if (checkPatterns[index] == true) {
                System.out.print(patterns[index]);
            }
        }

    }

    public static void main(String[] text) throws FileNotFoundException {

        String filePath = System.getProperty("user.dir") + "/";
        if (text.length > 0 && text.length == 1) {
            int counter = 0;
            File inputFile = new File(filePath + text[0]);
            Scanner scan = new Scanner(inputFile);
            while (scan.hasNextLine()) {

                counter++;
                System.out.print("Line " + counter + ":");
                String currentLine = scan.nextLine();
                char[] charCurrentLine = currentLine.toCharArray();
                new Test().checkAndDisplay(charCurrentLine);
                System.out.println();

            }

        } else if (text.length > 1) {
            System.out.println("Input too large to be processed!");
            System.exit(1);
        } else {
            System.out.println("No input could be processed");
            System.exit(1);
        }

    }
}