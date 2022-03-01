package com.example.demo1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class Login extends Application {
    static Stage window;
    @Override
    public void start(Stage stage) throws IOException {
        window=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 542, 400);
        window.setTitle("Wastewater Treatment Decision Making System");
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args) {
        launch();
    }
}