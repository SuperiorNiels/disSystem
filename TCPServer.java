package disSystem;


import java.io.*;
import java.net.*;

public class TCPServer {
    int port = 6969;
    public TCPServer(int port) {
        this.port = port;
    }

    public void server() {
        try {
            ServerSocket socket = new ServerSocket(port);
            while(true) {
                Socket s = socket.accept();
                Connection c = new Connection(s);
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
