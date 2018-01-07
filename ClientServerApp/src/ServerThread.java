import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerThread extends Thread {
    private Socket socket;
    private Statement statement;
    private ResultSet rS=null;

    public ServerThread(Socket socket, Statement statement) {
        this.socket = socket;
        this.statement=statement;
    }

    @Override
    public void run() {
        try {
            String message;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //czytaj od klienta
            PrintWriter out= new PrintWriter(socket.getOutputStream(),true);//wyslij do klienta
            while (!(message = in.readLine()).equals("exit")) {
                System.out.println(socket.getInetAddress() + " : " + message);
                if(message.equals("k"))
                {
                    try {
                        rS = statement.executeQuery("SELECT question from qa");
                        rS.next();
                        String m=rS.getString("question");
                        System.out.println(m);
                        out.println(m);
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }else
                    out.println("odpowiadam "+message);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
