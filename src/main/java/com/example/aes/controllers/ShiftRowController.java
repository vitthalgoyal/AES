package com.example.aes.controllers;

import com.example.aes.Converter;
import com.example.aes.Encoder;
import com.example.aes.Global;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShiftRowController implements Initializable {
    @FXML
    GridPane inputState;

    @FXML
    GridPane outputState;

    @FXML
    Button nextStage;

    @FXML
    Button nextRound;

    int[][] output;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                inputState.add(new Label(Converter.decimalToHex(Global.currentInput[i][j])),j,i);
            }
        }

        Encoder encoder = new Encoder();
        output = encoder.shiftRows(Global.currentInput);
       // System.out.println(outputState.getRowCount() + " " + outputState.getColumnCount());
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                //System.out.print(Converter.decimalToHex(output[i][j])+" ");
                outputState.add(new Label(Converter.decimalToHex(output[i][j])),j,i);
            }
            //System.out.println();
        }
    }

    @FXML
    public void nextRound(ActionEvent event) {

    }

    @FXML
    public void nextStage(ActionEvent event) {
        Global.currentInput = output;
        Stage stage = (Stage) inputState.getScene().getWindow();
        Global.nextStage(stage,"mix_column.fxml");
    }
}

