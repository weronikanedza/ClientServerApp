import javafx.fxml.FXML;

import javafx.event.ActionEvent;;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientController  {
    private ClientSocket clientSocket=new ClientSocket();
    private String error;
    @FXML private TextField niu;
    @FXML private Label niuLabel;

    @FXML
    public void logIn(ActionEvent event){

        clientSocket.connect();

        if (niu.getText().matches("[0-9]+") ) { //check whata data contains (only numbers)
                clientSocket.out.println("login "+niu.getText()); //send to server operation + data
            try {
                if(clientSocket.responseServer.readLine().equals("0")){ //niu accepted
                    System.out.println("zalogowano");
                    niuLabel.setText("");
                }else{
                    niuLabel.setText("Podany numer NIU został już użyty");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        } else {
            niuLabel.setText("Niu zawiera tylko liczby");
        }

           // clientSocket.disconnect();
    }


}
