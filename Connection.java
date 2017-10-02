package disSystem;

import java.io.*;
import java.net.*;

public class Connection extends Thread {
    Socket client;
    DataInputStream in;
    DataOutputStream out;
    String PATH = "/home/niels/Documents/";
    FileInputStream reader;
    public Connection(Socket s) {
        try {
            client = s;
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            this.start();
        }
        catch(IOException e) {
            System.out.println("Disconnected.");
        }
    }

    public void run() {
        try {
            String line = in.readUTF();
            try {
                File file = new File(PATH + line);
                reader = new FileInputStream(file);
                System.out.println("File '"+line+"' found.");
                out.writeBoolean(false);
                byte[] buffer = new byte[8192];
                int len;
                while ((len = reader.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.close();
                reader.close();
                System.out.println("File sent.");
            }
            catch(FileNotFoundException e) {
                System.out.println("File not found.");
                out.writeBoolean(true);
            }
        }
        catch(IOException e) {
            System.out.println("Disconnected.");
            //e.printStackTrace();
        }
        finally {
            try {
                client.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }
    }
}
