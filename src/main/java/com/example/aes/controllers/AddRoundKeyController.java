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
    GridPane roundKey;

    @FXML
    Button nextStage;

    @FXML
    Button nextRound;

    @FXML
    Button viewBtn;

    @FXML
    Label roundNo;

    int[][] output;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Global.currentRound == 0){
            roundNo.setText("Pre Round Transformation");
        }else
            roundNo.setText("Round No : " + Global.currentRound);

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
        Global.currentRound++;

        Stage stage = (Stage) inputState.getScene().getWindow();
        if(Global.currentRound == 11)
            Global.nextStage(stage,"output.fxml");
        else
            Global.nextStage(stage,"subbyte_transformation.fxml");
    }

    @FXML
    void nextStage(ActionEvent event) {
        nextRound(event);
    }

    @FXML
    void viewMatrix(ActionEvent event) {
        roundKey.setVisible(true);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                roundKey.add(new Label(Converter.decimalToHex(Global.currentKey[i][j])),j,i);
            }
        }
    }

}
