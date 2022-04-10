package com.example.demo1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
public class Login extends Application {
    static Stage window;
    static Stage splashStage;
    @Override
    public void start(Stage stage) throws IOException {
        splashStage= new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED);
        window=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 545, 375);
        window.setTitle("Wastewater Treatment Decision Making System");
        window.setResizable(false);
        splashStage.setTitle("Wastewater Treatment Decision Making System");
        splashStage.setScene(scene);
        splashStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        splashStage.setX((primScreenBounds.getWidth() - splashStage.getWidth()) / 2);
        splashStage.setY((primScreenBounds.getHeight() - splashStage.getHeight()) / 2);
    }
    public static void main(String[] args) {
        launch();
    }
}