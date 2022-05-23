package week13.q2;/*
 * PictureServer.java
 *
 * Version:
 *     $16.0.2$
 *
 * Revisions:
 *     $1.0$
 */

/**
 * server functionality
 *
 * @author Rishabh Arora
 * @author Vaidehi Kalra
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class PictureServer {

    private ServerSocket aServerSocket; // declaring server
    private final File[] file = new File[2];

    public PictureServer(int port) {
        startServerSocket(port);
    }  // starting the server after receiving the port

    /**
     * startServerSocket() starts the server on particular port.
     * Server starts listening
     *
     * @param port on which server has to listen
     */
    private void startServerSocket(int port) {
        try {
            aServerSocket = new ServerSocket(port);
            System.out.println("Listening on port: " + aServerSocket.getLocalPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It requests the image from server and initializes the array
     */
    public void run() {
        try {
            Socket clientSocket = aServerSocket.accept();
            while (true) {
                System.out.println(clientSocket.toString());
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(dis);
                Object input = in.readObject();
//                System.out.println("Server reading");
                if (input instanceof String) {
//                    System.out.println(input);
                    if (((String) input).contains("superman.txt")) {
                        readFile(dos, (String) input, 1);
                    } else if (((String) input).contains("batman.txt")) {
                        readFile(dos, (String) input, 0);
                    } else if ("stop".equalsIgnoreCase(((String) input))) {
                        closeObjects(dis, dos, in);
                        break;
                    }
                } else {
                    Vector<String> vec = (Vector<String>) input;
                    if (vec != null) {
                        //print progress
                        printVector(vec);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            closeObjects(clientSocket, out, in);
        }
    }

    /**
     * initializes the file array and sends the packet
     *
     * @param out   data output stream
     * @param input input
     * @param index index which is to be initialized
     */
    private void readFile(DataOutputStream out, String input, int index) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            String currentPath = System.getProperty("user.dir") + "/";
            file[index] = new File(currentPath + input);
            oos.writeObject(addImageCharToVectorObject(file[index]));
            sendPacket(out, baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Server is sending packet to client
     *
     * @param dos   output stream
     * @param bytes content to send
     * @throws IOException exception
     */
    private static void sendPacket(DataOutputStream dos, byte[] bytes) throws IOException {
        System.out.println("Server writing");
        dos.write(bytes);
        dos.flush();
    }

    /**
     * addImageToCharToVectorObject() adding the images to vector
     *
     * @param file which has to be read
     * @return vector with images
     * @throws FileNotFoundException exception handled in signature.
     */
    private static Vector<String> addImageCharToVectorObject(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        Vector<String> vec = new Vector<>();
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            char[] characterArray = row.toCharArray();
            for (int i = 0; i < characterArray.length; i++) {
                if (i == characterArray.length - 1)
                    vec.add("" + characterArray[i] + "\n");
                else if (i == 0) {
                    vec.add("\t\t" + characterArray[i]);
                } else
                    vec.add("" + characterArray[i]);
            }
        }
        return vec;
    }

    /**
     * printVector() prints out the resultant image
     *
     * @param vec vector which has to be read and printed
     */
    private static void printVector(Vector<String> vec) {
        for (String a : vec) {
            System.out.print(a);
        }
    }

    /**
     * closeObjects() closes the server socket connection, object output stream
     *
     */
    private void closeObjects(DataInputStream dis, DataOutputStream dos, ObjectInputStream in) {
        try {
            if (dis != null)
                dis.close();
            if (aServerSocket != null)
                aServerSocket.close();
            if (dos != null)
                dos.close();
            if (in != null)
                in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse the commandline arguments and sets variables.
     */
    private static void parseArgs(String[] args) {
        if (args.length == 2 && args[0].equals("-port")) {
            int port = Integer.parseInt(args[1]);
            new PictureServer(port).run();
        } else {
            System.err.println("Please pass correct arguments");
            System.exit(1);
        }
    }

    /**
     * main () to process arguments
     */
    public static void main(String[] argv) {
        parseArgs(argv);
    }

}