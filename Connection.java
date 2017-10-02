package disSystem;

import java.io.*;
import java.net.*;

public class Connection {
    Socket client;
    DataInputStream in;
    DataOutputStream out;
    public Connection(Socket s) {
        try {
            client = s;
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            while(true) {
                out.writeBytes("Hello from the server :)\n");
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
