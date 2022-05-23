package week13.q3;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

public class PictureServer {

    private static InetAddress ip;
    private static DatagramSocket ds;
    private static byte[] receive = new byte[6500];
    private final File[] file = new File[2];
    private final int port;

    public PictureServer(int port) {
        this.port = port;
        startServerSocket(port);
    }

    private void startServerSocket(int port) {
        try {
            ip = InetAddress.getLocalHost();
            ds = new DatagramSocket(port);
            System.out.println("Socket listening");
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                receivePacket(ds, receive);
                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(receive));
                Object input = in.readObject();
                System.out.println("Server reading");
                if (input instanceof String) {
                    if (((String) input).contains("superman.txt")) {
                        readFile((String) input, 1);
                    } else if (((String) input).contains("batman.txt")) {
                        readFile((String) input, 0);
                    } else if ("stop".equalsIgnoreCase(((String) input))) {
                        closeObjects(in);
                        break;
                    }
                } else {
                    Vector<String> vec = (Vector<String>) input;
                    if (vec != null) {
                        //print progress
                        printVector(vec);
                    }
                }
                receive = new byte[6500];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void receivePacket(DatagramSocket ds, byte[] receive) throws IOException {
        DatagramPacket dpReceive = new DatagramPacket(receive, receive.length);
        ds.receive(dpReceive);
    }

    private void readFile(String input, int index) {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            String currentPath = System.getProperty("user.dir") + "/";
            file[index] = new File(currentPath + input);
            oos.writeObject(addImageCharToVectorObject(file[index]));
            sendPacket(ds, baos.toByteArray(), (port + 10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendPacket(DatagramSocket ds, byte[] bytes, int port) throws IOException {
        DatagramPacket dpSend = new DatagramPacket(bytes, bytes.length, ip, port);
        ds.send(dpSend);
    }

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

    private static void printVector(Vector<String> vec) {
        for (String a : vec) {
            System.out.print(a);
        }
    }

    private void closeObjects(ObjectInputStream in) {
        try {
            if (ds != null)
                ds.close();
            if (in != null)
                in.close();
        } catch (Exception e) {
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

    public static void main(String[] argv) {
        parseArgs(argv);
    }

}