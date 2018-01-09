import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {

    private Socket socket;
    public  PrintWriter out;
    public  BufferedReader in;
    public  BufferedReader responseServer;


    public void connect(){
        try {
            socket=new Socket("localhost",2000);
            out = new PrintWriter(socket.getOutputStream(),true); //wyslij do serwera

            responseServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
