package disSystem;

import java.net.*;
import java.io.*;

public class UDPServer {
    private int port;
    FileInputStream reader;
    int BUFFER_SIZE = 1024*62;
    String PATH = "/home/niels/Documents/";
    public UDPServer(int port) {
        this.port = port;
    }

    public void server() {
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            DatagramSocket socket = new DatagramSocket(port);
            while(true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String input = new String(request.getData(),0,request.getLength());

                try {
                    File file = new File(PATH + input);
                    reader = new FileInputStream(file);
                    long size = file.length();
                    System.out.println("File '"+input+"' found. Sending "+size+" bytes.");

                    String size_string = Double.toString(Math.ceil((double) size/BUFFER_SIZE));
                    DatagramPacket size_packet = new DatagramPacket(size_string.getBytes()
                            ,size_string.length(), request.getAddress(), request.getPort());
                    socket.send(size_packet);

                    //send file
                    int len;
                    int i = 0;
                    DatagramPacket data;
                    while ((len = reader.read(buffer)) != -1) {
                        data = new DatagramPacket(buffer, len,
                                request.getAddress(), request.getPort());
                        socket.send(data);
                        i++;
                    }
                    System.out.println("Sent "+i+" packet(s).");
                    reader.close();
                }
                catch(FileNotFoundException e) {
                    String size_string = "0";
                    DatagramPacket size_packet = new DatagramPacket(size_string.getBytes()
                            ,size_string.length(), request.getAddress(), request.getPort());
                    socket.send(size_packet);
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}


