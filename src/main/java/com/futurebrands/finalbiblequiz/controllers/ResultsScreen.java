package com.futurebrands.finalbiblequiz.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResultsScreen {

    @FXML public ImageView resultsimage;
    @FXML private Label finalScore;
private String path;
    public void setScore(int scores) throws Exception {

        finalScore.setText(" " + scores);
        if(scores<=5){
            path = "src/main/resources/com/futurebrands/finalbiblequiz/assets/images/zerostars.png";
        }
        else if(scores>5 && scores <=8){
            path = "src/main/resources/com/futurebrands/finalbiblequiz/assets/images/oneStart.png";
        }else if(scores>8 && scores <=12){
            path = "src/main/resources/com/futurebrands/finalbiblequiz/assets/images/twoStars.png";
        }
        else if(scores>12){
            path = "src/main/resources/com/futurebrands/finalbiblequiz/assets/images/threeStars.png";
        }
        Image image = new Image("file:"+path);
        resultsimage.setImage(image);
        //System.out.println("my score " + scores);
    }
}
