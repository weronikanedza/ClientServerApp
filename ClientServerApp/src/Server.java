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
                new ServerThread(socket,database).start(); //kolejne watki dla klientow
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("exception");
        } finally {
            if (serverSocket != null)
                try {
                    database.closeConnection(); //zamkniecie bazy
                    System.out.println("ZAMKNIETOBAZE");
                    serverSocket.close(); //zamkniecie gniazda
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
