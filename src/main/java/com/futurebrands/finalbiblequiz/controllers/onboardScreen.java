package com.futurebrands.finalbiblequiz.controllers;

import com.futurebrands.finalbiblequiz.util.screenView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class onboardScreen {
    public StackPane mainbg;
    public Label topText;
    public ImageView logoImage;
    public VBox rulesContainer;
    public Button startQuizBtn;
    @FXML
    public Button btn1;


    @FXML
    public void quizScreen() {
      //  System.out.println("it has reached here");
        btn1.setOnAction(e -> {
            try {
                nextPage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void initialize() {
        {


        }

    }

    private void nextPage() throws IOException {
        Stage stage = (Stage) btn1.getScene().getWindow();
        screenView.load(stage, "/com/futurebrands/finalbiblequiz/views/home.fxml");
    }

}
