import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ClientController  {
    private ClientSocket clientSocket=new ClientSocket();
    private List<String> qalist,resultList;
    private String niuPerson,tab;
    @FXML
    private String answers="answer";

    @FXML private TextField niu;
    @FXML private Label niuLabel;
    @FXML private Label intNr;
    @FXML private Label userAnswers;
    @FXML private Label warningLabel1,warningLabel2,warningLabel3,warningLabel4,warningLabel5;
    @FXML private Label warningLabel6,warningLabel7,warningLabel8,warningLabel9,warningLabel10;
    @FXML private Label q1,q2,q3,q4,q5,q6,q7,q8,q9,q10;
    @FXML private Label a1_1,a1_2,a1_3,a1_4,a2_1,a2_2,a2_3,a2_4,a3_1,a3_2,a3_3,a3_4;
    @FXML private Label a4_1,a4_2,a4_3,a4_4,a5_1,a5_2,a5_3,a5_4,a6_1,a6_2,a6_3,a6_4;
    @FXML private Label a7_1,a7_2,a7_3,a7_4,a8_1,a8_2,a8_3,a8_4,a9_1,a9_2,a9_3,a9_4;
    @FXML private Label a10_1,a10_2,a10_3,a10_4;

    @FXML private AnchorPane firstpane;
    @FXML private AnchorPane finishSurvey;
    @FXML private AnchorPane currentpane;
    @FXML private AnchorPane lastPane;
    @FXML private AnchorPane question1,question2,question3,question4,question5,question6,question7,question8,question9,question10;

    @FXML private RadioButton r1_1,r1_2,r1_3,r1_4;
    @FXML private RadioButton r2_1,r2_2,r2_3,r2_4;
    @FXML private RadioButton r3_1,r3_2,r3_3,r3_4;
    @FXML private RadioButton r4_1,r4_2,r4_3,r4_4;
    @FXML private RadioButton r5_1,r5_2,r5_3,r5_4;
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
                clientSocket.out.println("login,"+niu.getText()); //send to server operation + data
                clientSocket.out.flush();
            try {
                if(clientSocket.responseServer.readLine().equals("0")){ //niu accepted
                   clientSocket.out.println("qa");
                   clientSocket.out.flush();
                    String qa=clientSocket.responseServer.readLine();
                    qalist = Arrays.asList(qa.split(","));
                    niuPerson=niu.getText();
                    answers+=","+niuPerson;
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
        setlabels(q1, r1_1, r1_2, r1_3, r1_4, 0);
        firstpane.setVisible(false);
        currentpane=question1;
        question1.setVisible(true);
    }

    public void question2(ActionEvent event){
       if( checkButtons(r1_1,r1_2,r1_3,r1_4).equals("0")) {
           setlabels(q2, r2_1, r2_2, r2_3, r2_4, 5);
           question1.setVisible(false);
           currentpane = question2;
           question2.setVisible(true);
       }else{
            warningLabel1.setVisible(true);
       }
    }

    public void question3(ActionEvent event){
        if( checkButtons(r2_1,r2_2,r2_3,r2_4).equals("0")) {
            setlabels(q3, r3_1, r3_2, r3_3, r3_4, 10);
            question2.setVisible(false);
            currentpane = question3;
            question3.setVisible(true);
        }else{
            warningLabel2.setVisible(true);
        }
    }
    public void question4(ActionEvent event){
        if( checkButtons(r3_1,r3_2,r3_3,r3_4).equals("0")) {
            setlabels(q4, r4_1, r4_2, r4_3, r4_4, 15);
            question3.setVisible(false);
            currentpane = question4;
            question4.setVisible(true);
        }else{
            warningLabel3.setVisible(true);
        }
    }

    public void question5(ActionEvent event){
        if( checkButtons(r4_1,r4_2,r4_3,r4_4).equals("0")) {
            setlabels(q5, r5_1, r5_2, r5_3, r5_4, 20);
            question4.setVisible(false);
            currentpane = question5;
            question5.setVisible(true);
        }else{
            warningLabel4.setVisible(true);
        }
    }
    public void question6(ActionEvent event){
        if( checkButtons(r5_1,r5_2,r5_3,r5_4).equals("0")) {
            setlabels(q6, r6_1, r6_2, r6_3, r6_4, 25);
            question5.setVisible(false);
            currentpane = question6;
            question6.setVisible(true);
        }else{
            warningLabel5.setVisible(true);
        }
    }
    public void question7(ActionEvent event){
        if( checkButtons(r6_1,r6_2,r6_3,r6_4).equals("0")) {
            setlabels(q7, r7_1, r7_2, r7_3, r7_4, 30);
            question6.setVisible(false);
            currentpane = question7;
            question7.setVisible(true);
        }else{
            warningLabel6.setVisible(true);
        }
    }
    public void question8(ActionEvent event){
        if( checkButtons(r7_1,r7_2,r7_3,r7_4).equals("0")) {
            setlabels(q8, r8_1, r8_2, r8_3, r8_4, 35);
            question7.setVisible(false);
            currentpane = question8;
            question8.setVisible(true);
        }else{
            warningLabel7.setVisible(true);
        }
    }

    public void question9(ActionEvent event){
        if( checkButtons(r8_1,r8_2,r8_3,r8_4).equals("0")) {
            setlabels(q9, r9_1, r9_2, r9_3, r9_4, 40);
            question8.setVisible(false);
            currentpane = question9;
            question9.setVisible(true);
        }else{
            warningLabel8.setVisible(true);
        }
    }

    public void question10(ActionEvent event){
        if( checkButtons(r9_1,r9_2,r9_3,r9_4).equals("0")) {
            setlabels(q10, r10_1, r10_2, r10_3, r10_4, 45);
            question9.setVisible(false);
            currentpane = question10;
            question10.setVisible(true);
        }else{
            warningLabel9.setVisible(true);
        }
    }

    public void lastScene(ActionEvent event){
        if( checkButtons(r10_1,r10_2,r10_3,r10_4).equals("0")) {
            clientSocket.out.println(answers); //send answers
            clientSocket.out.flush();

            try {
                tab=clientSocket.responseServer.readLine();
                resultList=Arrays.asList(tab.split(",")); //results list
                clientSocket.out.println("intNumber"); //number of interviewers
                clientSocket.out.flush();
                String number=clientSocket.responseServer.readLine();
                intNr.setText(number);

                clientSocket.out.println("myAnswer"+","+niuPerson+"");
                clientSocket.out.flush();
                String myAnswers=clientSocket.responseServer.readLine();
                userAnswers.setText(myAnswers);

            } catch (IOException e) {
                e.printStackTrace();
            }
            setAnswerLabels(a1_1,a1_2,a1_3,a1_4,1);
            setAnswerLabels(a2_1,a2_2,a2_3,a2_4,6);
            setAnswerLabels(a3_1,a3_2,a3_3,a3_4,11);
            setAnswerLabels(a4_1,a4_2,a4_3,a4_4,16);
            setAnswerLabels(a5_1,a5_2,a5_3,a5_4,21);
            setAnswerLabels(a6_1,a6_2,a6_3,a6_4,26);
            setAnswerLabels(a7_1,a7_2,a7_3,a7_4,31);
            setAnswerLabels(a8_1,a8_2,a8_3,a8_4,36);
            setAnswerLabels(a9_1,a9_2,a9_3,a9_4,41);
            setAnswerLabels(a10_1,a10_2,a10_3,a10_4,46);
            question10.setVisible(false);
            currentpane = lastPane;
            lastPane.setVisible(true);
        }else{
            warningLabel10.setVisible(true);
        }

    }

    public void setAnswerLabels(Label a,Label b,Label c,Label d,int i){
        a.setText(resultList.get(i++));
        b.setText(resultList.get(i++));
        c.setText(resultList.get(i++));
        d.setText(resultList.get(i++));

    }
    public String checkButtons(RadioButton r1,RadioButton r2,RadioButton r3,RadioButton r4){
        int i=0;
        if(r1.isSelected())
            answers+=",A";
        else i++;

        if(r2.isSelected())
            answers+=",B";
        else i++;

        if(r3.isSelected())
            answers+=",C";
        else i++;

        if(r4.isSelected())
            answers+=",D";
        else i++;

        if(i==4)
            return "1";
        else return "0";
    }

    public void setlabels(Label q,RadioButton r1,RadioButton r2, RadioButton r3,RadioButton r4 ,int listNumber){
        q.setText(qalist.get(listNumber++));
        r1.setText(qalist.get(listNumber++));
        r2.setText(qalist.get(listNumber++));
        r3.setText(qalist.get(listNumber++));
        r4.setText(qalist.get(listNumber++));
    }

    public void finish(ActionEvent event){
        clientSocket.out.println("exit");
        clientSocket.out.flush();
        clientSocket.disconnect();
        lastPane.setVisible(false);
        currentpane=finishSurvey;
        finishSurvey.setVisible(true);
    }

}
