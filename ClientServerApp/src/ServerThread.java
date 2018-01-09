import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServerThread extends Thread {
    private Socket socket;
    private Statement statement;
    private ResultSet rS=null;
    private SqlClass sql;

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
            sql=new SqlClass(statement); //class for sql select

            while (!(message = in.readLine()).equals("exit")) {
                System.out.println(socket.getInetAddress() + " : " + message);
                String[] splited = message.split(","); //get operation type

                switch (splited[0]){
                    case "login":
                        out.println(sql.checkNiu(splited[1]));
                        break;
                    case "qa":
                        out.println(sql.getQA());
                        break;
                    case "answer":
                        out.println(sql.answer(splited));
                        break;
                    case "intNumber":
                        out.println(sql.checkNumber());
                        break;
                    case "myAnswer":
                        out.println(sql.checkUserAnswers(splited[1]));
                        break;
                    default:break;
                }

            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


}
