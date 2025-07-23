package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import  javafx.scene.paint.Color;



import java.awt.*;
import java.io.IOException;

import static java.awt.Color.*;

public class HelloApplication extends Application {
    @Override //overrriding an abstract method from the apllication class
    public void start(Stage stage) throws IOException {
        //setup the root node
        Group root = new Group();
        Scene scene = new Scene(root,800,400, Color.BLUE);

        Text text = new Text();
        text.setText("wow i was just trying it");
        text.setX(80);
        text.setY(100);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Impact"));
        Rectangle rectangle = new Rectangle();
        rectangle.setX(300);
        rectangle.setY(60);
        rectangle.setWidth(400);
        rectangle.setHeight(200);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.CADETBLUE);
        rectangle.setStrokeWidth(3);
        //adding it to be a chile node
        root.getChildren().add(text);
        root.getChildren().add(rectangle);

        stage.setScene(scene);
        stage.setTitle("Learning Fx");

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
/*
first we have to create a stage which is basically a parent window then we allow it to show by  calling a show function
next we have to create the sceen which is a chile window but act as the main window to all the children node @kind of sandwich
Scene
Rectangles:set the x value and the y value.also set the width and height aswell then add it to the node


 */