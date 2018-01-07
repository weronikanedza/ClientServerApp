import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        Database database=new Database();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(2000);
            while (true) {
                Socket socket = serverSocket.accept(); //oczekiwanie na połączenie
                new ServerThread(socket,database.getStatement()).start(); //kolejne watki dla klientow
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (serverSocket != null)
                try {
                    database.closeConnection(); //zamkniecie bazy
                    serverSocket.close(); //zamkniecie gniazda
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
