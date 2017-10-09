package disSystem;

public class Main {

    public static void main(String[] args) {
        if (args[0].toLowerCase().equals("tcp")) {
            if (args[1].toLowerCase().equals("server")) {
                System.out.println("Starting TCP server");
                TCPServer server = new TCPServer(6969);
                server.server();
            } else if(args[1].equals("client")) {
                System.out.println("Starting TCP client");
                TCPClient client = new TCPClient(6969);
                client.connect();
            }
        }
        if (args[0].toLowerCase().equals("udp")) {
            if (args[1].equals("server")) {
                System.out.println("Starting UDP server");
                UDPServer server = new UDPServer(6969);
                server.server();
            } else if(args[1].toLowerCase().equals("client")) {
                System.out.println("Starting UDP client");
                UDPClient client = new UDPClient("localhost", 6969);
                client.connect();
            }
        }

        System.out.println("Please enter a parameter for the type of service.");
        System.out.println("Parameter 1: TCP/UDP\nParameter 2: server/client");
    }
}
