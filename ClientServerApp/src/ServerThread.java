import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServerThread extends Thread {
    private Socket socket;
    private Database database;
    private ResultSet rS=null;
    private SqlClass sql;

    public ServerThread(Socket socket, Database database) {
        this.socket = socket;
        this.database=database;
    }

    @Override
    public void run() {
        try {
            String message;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //czytaj od klienta
            PrintWriter out= new PrintWriter(socket.getOutputStream(),true);//wyslij do klienta
            sql=new SqlClass(database.getStatement()); //class for sql select

            do {
                message = in.readLine();
            //    System.out.println(socket.getInetAddress() + " : " + message);
                String[] splited = message.split(","); //get operation type

                synchronized (this) {
                    switch (splited[0]) {
                        case "login":
                            out.println(sql.checkNiu(splited[1]));
                            out.flush();
                            break;
                        case "qa":
                            out.println(sql.getQA());
                            out.flush();
                            break;
                        case "answer":
                            out.println(sql.answer(splited));
                            out.flush();
                            break;
                        case "intNumber":
                            out.println(sql.checkNumber());
                            out.flush();
                            break;
                        case "myAnswer":
                            out.println(sql.checkUserAnswers(splited[1]));
                            out.flush();
                            break;

                        default:
                            break;
                    }
                }
            }while(!message.equals("exit"));
            database.closeConnection();
            System.out.println("koniec");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


}
