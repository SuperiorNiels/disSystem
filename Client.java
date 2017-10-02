package disSystem;

import java.io.*;
import java.net.*;

public class Client {
    int port = 6969;
    DataInputStream in;
    DataOutputStream out;
    public Client(int port) {
        this.port = port;
    }

    public void connect() {
        try {
            Socket s = new Socket("localhost",port);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            while(true) {
                String line = in.readUTF();
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }
}