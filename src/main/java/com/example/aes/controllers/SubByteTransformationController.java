package com.example.aes.controllers;

import com.example.aes.Converter;
import com.example.aes.Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SubByteTransformationController implements Initializable {

    @FXML
    GridPane inputState;

    @FXML
    GridPane outputState;

    @FXML
    Button nextStage;

    @FXML
    Button nextRound;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                inputState.add(new Label(Converter.decimalToHex(Global.currentInput[i][j])),i,j);
            }
        }
    }

    @FXML
    public void nextRound(ActionEvent event) {

    }

    @FXML
    public void nextStage(ActionEvent event) {

    }
}
