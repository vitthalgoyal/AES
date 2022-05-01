package com.example.aes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Global {
    public static String plaintext;
    public static String key;

    public static int[][] currentInput;
    public static int[][] currentKey;
    public static int currentRound;
    public static int[][] currentRCon;

    public static void nextStage(Stage stage,String path){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(path)));
            stage.setScene(new Scene(root,800,600));
            stage.setTitle("AES Visualizer");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
