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

import java.io.InputStream;

public class ResultsScreen {

    @FXML public ImageView resultsimage;
    @FXML public Button playAgainbtn;
    @FXML private Label finalScore;
    private String path;

    public void setScore(int scores) throws Exception {
        finalScore.setText(" " + scores);

        if (scores <= 5) {
            path = "/com/futurebrands/finalbiblequiz/assets/images/zerostars.png";
        } else if (scores > 5 && scores <= 8) {
            path = "/com/futurebrands/finalbiblequiz/assets/images/oneStart.png";
        } else if (scores > 8 && scores <= 12) {
            path = "/com/futurebrands/finalbiblequiz/assets/images/twoStars.png";
        } else if (scores > 12) {
            path = "/com/futurebrands/finalbiblequiz/assets/images/threeStars.png";
        }

        // Load image from resources (works inside JAR and in IDE)
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("Image resource not found: " + path);
            }
            Image image = new Image(is);
            resultsimage.setImage(image);
        }
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
