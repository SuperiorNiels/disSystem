package disSystem;

import java.net.*;
import java.io.*;

public class UDPServer {
    int port;
    FileInputStream reader;
    String PATH = "/home/niels/Documents/";
    public UDPServer(int port) {
        this.port = port;
    }

    public void server() {
        byte[] buffer = new byte[8192];
        try {
            DatagramSocket socket = new DatagramSocket(port);
            while(true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String input = new String(request.getData(),0,request.getLength());

                String response = "NOT_FOUND";
                try {
                    File file = new File(PATH + input);
                    reader = new FileInputStream(file);
                    response = "FOUND";

                    byte[] to_send = response.getBytes();
                    DatagramPacket reply = new DatagramPacket(to_send, to_send.length,
                            request.getAddress(), request.getPort());
                    socket.send(reply);

                    int i = 0;
                    while (reader.read(buffer) != -1) {
                        DatagramPacket data = new DatagramPacket(buffer, buffer.length,
                                request.getAddress(), request.getPort());
                        socket.send(data);
                        System.out.println("Pakket nr. "+i);
                        i++;
                    }

                    reader.close();
                }
                catch(FileNotFoundException e) {
                    byte[] to_send = response.getBytes();
                    DatagramPacket reply = new DatagramPacket(to_send, to_send.length,
                            request.getAddress(), request.getPort());
                    socket.send(reply);
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}


