package com.futurebrands.finalbiblequiz.util;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class screenView {

    private static final double INIT_WIDTH = 500;
    private static final double INIT_HEIGHT = 700;

    private static final double MIN_WIDTH =500;
    private static final double MIN_HEIGHT = 650;

    public static void load(Stage stage, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(screenView.class.getResource(fxmlPath));
        Region content = loader.load();

        Scene scene = new Scene(content, INIT_WIDTH, INIT_HEIGHT);
        //scene.getStylesheets().add(
          //      Objects.requireNonNull(screenView.class.getResource("com/futurebrands/finalbiblequiz/assets/mainStyles.css")).toExternalForm()
       // );

        // Make window responsive and resizable
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setResizable(true);
        stage.setMaxHeight(INIT_HEIGHT);
        stage.setMaxWidth(INIT_WIDTH);

        if (!stage.isShowing()) {
            stage.initStyle(StageStyle.DECORATED);
        }

        stage.setScene(scene);

        // Use current stage size if different from initial; otherwise use initial size
        double widthToUse = (stage.getWidth() > 0 && stage.getWidth() != INIT_WIDTH) ? stage.getWidth() : INIT_WIDTH;
        double heightToUse = (stage.getHeight() > 0 && stage.getHeight() != INIT_HEIGHT) ? stage.getHeight() : INIT_HEIGHT;

        stage.setWidth(widthToUse);
        stage.setHeight(heightToUse);

        // Center the window on screen only if size is initial (optional)
        if (widthToUse == INIT_WIDTH && heightToUse == INIT_HEIGHT) {
            stage.centerOnScreen();
        }

        if (!stage.isShowing()) {
            stage.show();
        }

        // Bind Region to Scene for automatic resize
        content.prefWidthProperty().bind(scene.widthProperty());
        content.prefHeightProperty().bind(scene.heightProperty());
    }
    public static void load2(Stage stage, Parent root) {
        Scene scene = new Scene(root, INIT_WIDTH, INIT_HEIGHT);

        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setResizable(true);
        stage.setMaxHeight(INIT_HEIGHT);
        stage.setMaxWidth(INIT_WIDTH);

        if (!stage.isShowing()) {
            stage.initStyle(StageStyle.DECORATED);
        }

        stage.setScene(scene);

        double widthToUse = (stage.getWidth() > 0 && stage.getWidth() != INIT_WIDTH) ? stage.getWidth() : INIT_WIDTH;
        double heightToUse = (stage.getHeight() > 0 && stage.getHeight() != INIT_HEIGHT) ? stage.getHeight() : INIT_HEIGHT;

        stage.setWidth(widthToUse);
        stage.setHeight(heightToUse);

        if (widthToUse == INIT_WIDTH && heightToUse == INIT_HEIGHT) {
            stage.centerOnScreen();
        }

        if (!stage.isShowing()) {
            stage.show();
        }

        // Bind root layout (Parent) to scene for resizing (only if it's a Region)
        if (root instanceof Region regionRoot) {
            regionRoot.prefWidthProperty().bind(scene.widthProperty());
            regionRoot.prefHeightProperty().bind(scene.heightProperty());
        }
    }

}