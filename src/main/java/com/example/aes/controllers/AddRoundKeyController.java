package com.example.aes.controllers;

import com.example.aes.Converter;
import com.example.aes.Encoder;
import com.example.aes.Global;
import com.example.aes.KeyExpansion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoundKeyController implements Initializable {

    @FXML
    GridPane inputState;

    @FXML
    GridPane outputState;

    @FXML
    Button nextStage;

    @FXML
    Button nextRound;

    @FXML
    Button viewBtn;

    int[][] output;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                inputState.add(new Label(Converter.decimalToHex(Global.currentInput[i][j])),j,i);
            }
        }

        Encoder encoder = new Encoder();
        output = encoder.addRoundKey(Global.currentInput);

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                outputState.add(new Label(Converter.decimalToHex(output[i][j])),j,i);
            }
        }
    }

    @FXML
    void nextRound(ActionEvent event) {
        Global.currentInput = output;
        KeyExpansion keyExpansion = new KeyExpansion();
        keyExpansion.expandKey();

        Stage stage = (Stage) inputState.getScene().getWindow();
        Global.nextStage(stage,"subbyte_transformation.fxml");
    }

    @FXML
    void nextStage(ActionEvent event) {
        nextRound(event);
    }

    @FXML
    void viewMatrix(ActionEvent event) {

    }

}
