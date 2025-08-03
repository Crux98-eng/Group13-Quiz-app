package com.futurebrands.finalbiblequiz.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultsScreen {

    @FXML private Label finalScore;

    public void setScore(int scores) throws Exception {

        finalScore.setText(" " + scores);
        System.out.println("my score " + scores);
    }
}
