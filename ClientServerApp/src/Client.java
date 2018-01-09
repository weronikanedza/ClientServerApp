//import java.io.*;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Client {
//    public static void main(String[] args) {
//
//        try {
//            Socket socket=new Socket("localhost",2000);
//            PrintWriter out = new PrintWriter(socket.getOutputStream(),true); //wyslij do serwera
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); //zczytaj
//
//            BufferedReader responseServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//           while (true) {
//               String readInput = in.readLine();
//               out.println(readInput);
//               System.out.println("response "+ responseServer.readLine());
//           }
//
//           //socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
