package disSystem;

public class Main {

    public static void main(String[] args) {
        TCPServer server = new TCPServer(6969);
        server.server();
    }
}
