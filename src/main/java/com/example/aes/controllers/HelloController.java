package com.example.aes.controllers;

import com.example.aes.Converter;
import com.example.aes.Global;
import com.example.aes.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Random;

public class HelloController {

    @FXML
    public TextField plaintext;

    @FXML
    public TextField key;

    @FXML
    public Button encryptBtn;

    @FXML
    public void encrypt(ActionEvent actionEvent){
        String _plaintext = plaintext.getText().toUpperCase().replaceAll(" ","");  //extracting plaintext and key.
        String _key = key.getText().toUpperCase().replaceAll(" ","");

        if(_plaintext.length() != 32 || _key.length() != 32){ //if plaintext or key is not 128 bit.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Invalid data.");
            alert.showAndWait();
        }else{
            Global.currentInput = getBytesFromString(_plaintext);
            Global.currentKey = getBytesFromString(_key);
            Global.currentRound = 0;
            Global.currentRCon = new int[][]{{0x01}, {0x00}, {0x00}, {0x00}};

            try {
                Stage stage = (Stage) encryptBtn.getScene().getWindow();
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("subbyte_transformation.fxml"));
                stage.setTitle("AES Encryption Visualizer");
                stage.setScene(new Scene(root,900,600));
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    int[][] getBytesFromString(String s){
        int[][] bytes = new int[4][4];
        for(int i=0;i<32;i+=2){
            int idx = i/2;
            bytes[idx/4][idx%4] = Converter.hexToDecimal(s.substring(i,i+2));
        }

        int[][] transform = new int[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++)
                transform[i][j] = bytes[j][i];
        }

        return transform;
    }

    @FXML
    public void randomGenerate(ActionEvent event){
        Random random = new Random();
        StringBuilder _plaintext = new StringBuilder();
        StringBuilder _key= new StringBuilder();
        for(int i=0;i<16;i++){
            int a = Math.abs(random.nextInt(256));
            int b = Math.abs(random.nextInt(256));
            _plaintext.append(Converter.decimalToHex(a));
            _plaintext.append(" ");
            _key.append(Converter.decimalToHex(b));
            _key.append(" ");
        }

        plaintext.setText(_plaintext.toString());
        key.setText(_key.toString());
    }
}