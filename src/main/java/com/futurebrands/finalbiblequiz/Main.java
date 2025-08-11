package com.futurebrands.finalbiblequiz;


import com.futurebrands.finalbiblequiz.util.screenView;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
      this.primaryStage = stage;

      // primaryStage.setTitle("Bible Quiz");
    // primaryStage.show();
        //screenView.load(primaryStage,"/com/futurebrands/finalbiblequiz/views/home.fxml");
         screenView.load(primaryStage,"/com/futurebrands/finalbiblequiz/views/onboardScreen.fxml");


    }

    public static void main(String[] args) {
        launch(args);
    }
}
