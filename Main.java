package disSystem;

public class Main {

    public static void main(String[] args) {
        //Servers
        //TCPServer server = new TCPServer(6969);
        UDPServer server = new UDPServer(6969);
        server.server();

        //Clients
        //TCPClient client = new TCPClient(6969);
        //UDPClient client = new UDPClient("localhost", 6969);
        //client.connect();
    }
}
