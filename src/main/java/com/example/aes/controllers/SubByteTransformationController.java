package com.example.aes.controllers;

import com.example.aes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.security.KeyException;
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

    @FXML
    Label roundNo;

    private Encoder encoder;
    private int[][] output;

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

        encoder = new Encoder();
        output = encoder.subBytes(Global.currentInput);

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                outputState.add(new Label(Converter.decimalToHex(output[i][j])),j,i);
            }
        }
    }

    @FXML
    public void nextRound(ActionEvent event) {
        KeyExpansion keyExpansion = new KeyExpansion();
        output = encoder.shiftRows(output);
        output = encoder.mixColumns(output);
        output = encoder.addRoundKey(output);

        Global.currentRound++;
        keyExpansion.expandKey();
        Global.currentInput = output;

        Stage stage = (Stage) inputState.getScene().getWindow();
        if(Global.currentRound == 11)
            Global.nextStage(stage,"output.fxml");
        else
            Global.nextStage(stage,"subbyte_transformation.fxml");
    }

    @FXML
    public void nextStage(ActionEvent event) {
        Global.currentInput = output;
        Stage stage = (Stage) inputState.getScene().getWindow();
        Global.nextStage(stage,"shift_row.fxml");
    }

    @FXML
    public void viewMatrix(ActionEvent event){

    }
}
