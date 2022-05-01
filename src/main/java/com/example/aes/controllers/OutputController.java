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
        plaintext.setText(Global.plaintext);
        key.setText(Global.key);

        StringBuilder text = new StringBuilder();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                text.append(Converter.decimalToHex(Global.currentInput[j][i]));
            }
        }

        encryptedText.setText(text.toString());
    }
}
