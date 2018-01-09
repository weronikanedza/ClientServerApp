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
            in = new BufferedReader(new InputStreamReader(System.in)); //zczytaj

            responseServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            while (true) {
//                String readInput = in.readLine();
//                out.println(readInput);
//                System.out.println("response "+ responseServer.readLine());
//            }

            //socket.close();
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
