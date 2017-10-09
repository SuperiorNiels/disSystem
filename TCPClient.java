package disSystem;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    int port = 6969;
    DataInputStream in;
    DataOutputStream out;
    Scanner input;
    FileOutputStream writer;
    String SAVEPATH = "/home/niels/tcpTest/";
    public TCPClient(int port) {
        this.port = port;
    }

    public void connect() {
        input = new Scanner(System.in);
        while(true) {
            try {
                Socket s = new Socket("localhost", 6969);
                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());

                String command = input.nextLine();
                out.writeUTF(command);
                if(!in.readBoolean()) {
                    writer = new FileOutputStream(SAVEPATH + command);
                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        writer.write(buffer, 0, len);
                    }
                    System.out.println("File recieved.");
                    writer.close();
                }

                in.close();
                out.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
