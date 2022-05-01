package com.example.aes.controllers;

import com.example.aes.Converter;
import com.example.aes.Global;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class OutputController implements Initializable {

    @FXML
    TextField plaintext;

    @FXML
    TextField key;

    @FXML
    TextField encryptedText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder text = new StringBuilder();
        StringBuilder _plaintext= new StringBuilder();
        StringBuilder _key= new StringBuilder();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                text.append(Converter.decimalToHex(Global.currentInput[j][i])).append(" ");
                _plaintext.append(Converter.decimalToHex(Global.plaintext[j][i])).append(" ");
                _key.append(Converter.decimalToHex(Global.key[j][i])).append(" ");
            }
        }

        plaintext.setText(_plaintext.toString());
        key.setText(_key.toString());
        encryptedText.setText(text.toString());
    }
}
