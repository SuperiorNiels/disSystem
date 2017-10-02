package disSystem;

import java.io.*;
import java.net.*;

public class TCPServer {
    //geef een poort nummer die vrij is ( best hoger als 2000)
    int port = 6969;
    public TCPServer(int port) {
        this.port = port;
    }

    public void server() {
        try {
            //maak een serverSocket, deze wacht op een request
            ServerSocket socket = new ServerSocket(port);
            while(true) {
                Socket clientSocket = socket.accept();
                //als er iets binnen komt maak dan een connectie
                Connection c = new Connection(clientSocket);
            }
        }
        catch(IOException e) {
            System.out.println(e);
        }
    }
}
