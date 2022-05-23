/*
 * Regular Expression.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This program takes the argument and reads a file and
 * finds the word that matches with the pattern.
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

public class RegularExpression {
    static String[] patternStringArray; //stores the strings that are to be reached
    static  String delimiter; //stores the delimiter
    static String currentPath=""; //stores the current path
    static  String fileInput = ""; //reads the string
    static File file; //creates a file object
    static  boolean fileFound; // stores if file is found
    static String[] allPatternsToTest ={
        "a word which is one character long", "^.$",
        "a word which is two characters long", "^..$",
        "a word which is two or more characters long", "^...*$",
        "a word with the vowels ’aeiou’ in order and each vowel can appear only once",
        "^[^aeiou]*a[^aeiou]*e[^aeiou]*i[^aeiou]*o[^aeiou]*u[^aeiou]*$",
        "includes ac/dc or AC/DC", "^.*((a|d)c|(A|D)C).*$",
        "includes only lower case characters, but not the character ’h’, ’p’, and ’b’", "^[a-z && [^hpb]]*$",
        "starts with ’(’, followed by ’one’ or ’uno’, or ’eins’, followed by ’)’", "^\\((one|uno|eins)\\)$",
        "starts with ’M’ or ’m’ followed ’oma’", "^[mM]oma$",
        "starts with ’[’, followed by ’a-m’ or ’O-Z’, followed by ’]’", "^\\[(a-m|O-Z)\\]$",
        "starts with ’a’ followed by 2 digits in the range between 1 to 3 only", "^a[1-3]{2}$",
        "starts with ’a’ followed by one or more digits", "^a\\d+$",
        "starts with 2 lower case characters’ followed by 3 digits", "^[a-z]{2}[0-9]{3}$"
    };

    /**
     * The initArguments method processes the arguments passed.
     * It also reads the file and stores in a string
     *
     * @param       args        command line arguments were passed
     *                          from main()
     * @return                  returns a boolean value from
     */
    public static boolean initArguments(String[] args){
        boolean flag = false;
        for (int index = 0; index < args.length; index++) {
            if(args[index].equals("-input")){

                if(!args[index+1].equals("-")){
                    flag = true;
                    break;
                }
            }
        }
        return  flag;
    }

    /**
     * The storeFileInString method processes the arguments passed.
     * It also reads the file and stores in a string
     *
     * @param       args        command line arguments were passed
     *                          from main()
     * @return                  returns a boolean value from
     */
    public  static void storeFileInString(String []args){
        Scanner sc = null;  //initializes the scanner object to null
        String str="";
        try {
            currentPath = System.getProperty("user.dir") + "\\" + "src" + "\\";

            for (int index = 0; index < args.length; index++) {
                if(args[index].equals("-input")){
                    if(args[index+1] != "-"){
                        str += args[index+1];
                        break;
                    }
                }
            }
            file = new File(currentPath +  str); //initializes the file object
            sc = new Scanner(file); //
            while (sc.hasNextLine()) {
                fileInput += (sc.nextLine());  //appends the data into string from file

            }

        } catch (FileNotFoundException ignored) {

        } finally {
            if (sc != null)
                sc.close();
        }

    }

    /**
     * The getFileInputInString method processes the fileInput and uses the delimiter
     * to split and stores in a string type array.
     */
    public  static void getFileInputInString(){
        patternStringArray = fileInput.split(delimiter);
    }

    /**
     * The takeInputFromStdIn method processes the arguments passed.
     * It gets delimiter from command line and stores in a variable.
     */
    public  static void takeInputFromStdIn(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        String str = sc.nextLine();
        getDelimiter(args);
        patternStringArray = str.split(":");
    }

    /**
     * The getDelimiter method processes the arguments passed.
     * It gets delimiter from command line and stores in a variable.
     */
    public static void getDelimiter(String[] args){
        for (int index = 0; index < args.length; index++) {
            if(args[index].equals("-d")){
                delimiter = args[index + 1];
            }
        }
    }

    /**
     * The processStatic method finds the words with corresponding
     * pattern and displays them on console.
     */
    public static void processStatic(){
        for (int i = 0; i < patternStringArray.length; i++) {
            System.out.println("--------------------------------------------------");
            System.out.println("Input: -" + patternStringArray[i] + "=");

            for (int j = 1; j < allPatternsToTest.length; j+=2){
                if(Pattern.matches(allPatternsToTest[j],patternStringArray[i]) ){
                    System.out.println("This regular expression " + allPatternsToTest[j] +" matches the following input: -"  + patternStringArray[i] + "=\n" +
                            "\tverbal explanation: " + allPatternsToTest[j-1]);
                }
            }
        }
    }

    /**
     * The main program which calls the methods which
     * finds out the matching pattern using an algorithm.
     *
     * @param       args        command line arguments
     */
    public static void main(String[] args){
    fileFound =initArguments(args);

    if(fileFound== true){
        getDelimiter(args);
        storeFileInString(args);
        getFileInputInString();
    }
    else {
        takeInputFromStdIn(args);
    }
    processStatic();
    }
} //RegularExpression
