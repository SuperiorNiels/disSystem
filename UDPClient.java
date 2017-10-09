package disSystem;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    int port;
    String address;
    int BUFFER_SIZE = 1024*62;
    String PATH = "/home/niels/udpTest/";
    int client_port = 6970;
    public UDPClient(String address, int port) {
        this.address = address;
        this.port = port; // server port
    }

    public void connect() {
        Scanner input;
        byte[] buffer = new byte[BUFFER_SIZE];
        while(true) {
            DatagramSocket socket;
            try {
                socket = new DatagramSocket(client_port);
                socket.setSoTimeout(10000);
                input = new Scanner(System.in);
                String name = input.nextLine();
                byte[] nameBytes = name.getBytes();
                DatagramPacket to_send = new DatagramPacket(nameBytes,nameBytes.length,
                        InetAddress.getByName(address),port);
                socket.send(to_send);
                DatagramPacket data = new DatagramPacket(buffer, buffer.length);
                socket.receive(data);

                long number_of_packets = Double.valueOf(new String(data.getData(), 0, data.getLength())).longValue();
                System.out.println(number_of_packets);
                if (number_of_packets > 0) {
                    FileOutputStream writer = new FileOutputStream(PATH + name);
                    int i = 0;
                    while (number_of_packets > i) {
                        socket.receive(data);
                        writer.write(data.getData(), 0, data.getLength());
                        i++;
                        System.out.println("Packet: "+i);
                    }

                    writer.close();
                    System.out.println("File recieved.");
                } else {
                    System.out.println("File not found on server.");
                }

                socket.close();
            }
            catch (SocketTimeoutException e) {
                client_port++;
                socket = null;
                System.out.println("File not recieved correctly.");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
