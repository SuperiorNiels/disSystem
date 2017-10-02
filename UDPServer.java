package disSystem;

/**
 * Created by wubben on 2-10-2017.
 */

import java.net.*;
import java.io.*;

public class UDPServer {

        public static void main(String args[]){
            DatagramSocket aSocket = null;

            try{
                aSocket = new DatagramSocket(6969);
                byte []buffer = new byte[1000];
                while(true){
                    //maak leeg packet
                    DatagramPacket request =new DatagramPacket(buffer, buffer.length);
                    //vul het packet als het aankomt
                    aSocket.receive(request);
                    //maak een nieuw packet "reply" en stuur dit terug
                    DatagramPacket reply = new DatagramPacket(request.getData(),
                    request.getLength(),request.getAddress(), request.getPort());
                    aSocket.send(reply);
                }
            }catch (SocketException e){
                System.out.println(e);
            }catch (IOException e){System.out.println(e);}
        finally{if (aSocket !=null)aSocket.close();}
}
