package com.futurebrands.finalbiblequiz.controllers;

import com.futurebrands.finalbiblequiz.util.screenView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ResultsScreen {

    @FXML public ImageView resultsimage;
    @FXML public Button playAgainbtn;
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
    public void handlenextScreen(){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/futurebrands/finalbiblequiz/views/home.fxml"));

                // Load the root and get the controller
                Parent root = loader.load();

                // Use a new stage to display the result screen
                // Stage stage = new Stage();
                Stage stage = (Stage) playAgainbtn.getScene().getWindow();

                screenView.load2(stage, root);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

    }
}
