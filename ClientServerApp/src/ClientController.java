import javafx.fxml.FXML;

import javafx.event.ActionEvent;;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ClientController  {
    private ClientSocket clientSocket=new ClientSocket();
    List<String> qalist;

    @FXML private TextField niu;
    @FXML private Label niuLabel;
    @FXML private AnchorPane firstpane;
    @FXML private AnchorPane currentpane;
    @FXML private AnchorPane question1,question2,question3,question4,question5,question6,question7,question8,question9,question10;
    @FXML private Label q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;
    @FXML private RadioButton r1_1,r1_2,r1_3,r1_4;
    @FXML private RadioButton r2_1,r2_2,r2_3,r2_4;
    @FXML private RadioButton r3_1,r3_2,r3_3,r3_4;
    @FXML private RadioButton r4_1,r4_2,r4_3,r4_4;
    @FXML private RadioButton r5_1,r5_2,r_3,r5_4;
    @FXML private RadioButton r6_1,r6_2,r6_3,r6_4;
    @FXML private RadioButton r7_1,r7_2,r7_3,r7_4;
    @FXML private RadioButton r8_1,r8_2,r8_3,r8_4;
    @FXML private RadioButton r9_1,r9_2,r9_3,r9_4;
    @FXML private RadioButton r10_1,r10_2,r10_3,r10_4;

    public ClientController() {
        clientSocket.connect();
    }

    @FXML
    public void logIn(ActionEvent event){

        if (niu.getText().matches("[0-9]+") ) { //check whata data contains (only numbers)
                clientSocket.out.println("login "+niu.getText()); //send to server operation + data
            try {
                if(clientSocket.responseServer.readLine().equals("0")){ //niu accepted
                   clientSocket.out.println("qa");
                    String qa=clientSocket.responseServer.readLine();
                    qalist = Arrays.asList(qa.split(","));
                    question1();
                }else{
                    niuLabel.setText("Podany numer NIU został już użyty");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }




        } else {
            niuLabel.setText("Niu zawiera tylko liczby");
        }

    }

    public void counter(){
        if(r1_1.isPressed())
            System.out.println("halo");
    }



    public void question1(){
        q1.setText(qalist.get(0));
        r1_1.setText(qalist.get(1));
        r1_2.setText(qalist.get(2));
        r1_3.setText(qalist.get(3));
        r1_4.setText(qalist.get(4));
        firstpane.setVisible(false);
        currentpane=question1;
        question1.setVisible(true);
    }

    public void question2(){
        q2.setText(qalist.get(5));
        r2_1.setText(qalist.get(6));
        r2_2.setText(qalist.get(7));
        r2_3.setText(qalist.get(8));
        r2_4.setText(qalist.get(9));
        firstpane.setVisible(false);
        currentpane=question2;
        question2.setVisible(true);
    }





}
