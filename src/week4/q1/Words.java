package week4.q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Words {
    public String getWord(String fileName) throws FileNotFoundException {
        String currentPath = System.getProperty("user.dir") + "/";
        File file = new File(currentPath + fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String word = sc.nextLine();
            if (!word.isEmpty()) {
                return word.toLowerCase().trim();
            }
        }
        return "";
    }
}
