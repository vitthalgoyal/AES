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

public class MixColumnController implements Initializable {

    @FXML
    GridPane inputState;

    @FXML
    GridPane outputState;

    @FXML
    GridPane constantMatrix;

    @FXML
    Button nextStage;

    @FXML
    Button nextRound;

    @FXML
    Button viewBtn;

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
        output = encoder.mixColumns(Global.currentInput);

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                outputState.add(new Label(Converter.decimalToHex(output[i][j])),j,i);
            }
        }
    }

    @FXML
    void nextRound(ActionEvent event) {
        KeyExpansion keyExpansion = new KeyExpansion();
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
    void nextStage(ActionEvent event) {
        Global.currentInput = output;
        Stage stage = (Stage) inputState.getScene().getWindow();
        Global.nextStage(stage,"add_round_key.fxml");
    }

    @FXML
    void viewMatrix(ActionEvent event) {
        constantMatrix.setVisible(true);
        int[][] matrix = new int[4][4];
        matrix[0][0] = 0x02;
        matrix[0][1] = 0x03;
        matrix[0][2] = 0x01;
        matrix[0][3] = 0x01;
        matrix[1][0] = 0x01;
        matrix[1][1] = 0x02;
        matrix[1][2] = 0x03;
        matrix[1][3] = 0x01;
        matrix[2][0] = 0x01;
        matrix[2][1] = 0x01;
        matrix[2][2] = 0x02;
        matrix[2][3] = 0x03;
        matrix[3][0] = 0x03;
        matrix[3][1] = 0x01;
        matrix[3][2] = 0x01;
        matrix[3][3] = 0x02;

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                constantMatrix.add(new Label(""+matrix[i][j]),j,i);
            }
        }
    }

}
