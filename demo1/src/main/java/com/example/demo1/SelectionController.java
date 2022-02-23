package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;


public class SelectionController {
    @FXML
    private Button selectButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField UnselectedTextField;

    @FXML
    private TextField SelectedTextField;

    @FXML
    private TableColumn<Print,String> UnselectedStage;

    @FXML
    private TableColumn<Print,String> UnselectedModel;

    @FXML
    private TableColumn<Print,String> SelectedStage;

    @FXML
    private TableColumn<Print,String> SelectedModel;

    @FXML
    private TableView<Print> UnselectedTable;

    @FXML
    private TableView<Print> SelectedTable;

    @FXML
    protected void backButtonOnAction(){
        FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("Menu-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 585, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);

    }

    @FXML
    protected void selectButtonOnAction(){

    }
}
