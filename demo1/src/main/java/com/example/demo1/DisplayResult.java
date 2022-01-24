package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class DisplayResult {
    ObservableList<String> Preference = FXCollections.observableArrayList("Best Overall","Cost Effectiveness ");

    @FXML
    protected void BackButtonOnAction(){
        FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("WaterChar-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 585, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
    }
    @FXML
    private ComboBox<String> SelectPreference;

    @FXML
    private TableView<Print> ResultView;

    @FXML
    private TableColumn<Print,String> Preliminary;

    @FXML
    private TableColumn<Print,String> Chemical;

    @FXML
    private TableColumn<Print,String> Biological;

    @FXML
    private TableColumn<Print,String> Tertiary;

    @FXML
    private TableColumn<Print,String> Sludge;

    @FXML
    private TableColumn<Print,Double> TSS;

    @FXML
    private TableColumn<Print,Double> COD;

    @FXML
    private TableColumn<Print,Double> BOD;

    @FXML
    private TableColumn<Print,Double> COST;

    @FXML
    private void initialize() {
        SelectPreference.setItems(Preference);
        Preliminary.setCellValueFactory(new PropertyValueFactory<>("treatmentsA"));
        Chemical.setCellValueFactory(new PropertyValueFactory<>("treatmentsB"));
        Biological.setCellValueFactory(new PropertyValueFactory<>("treatmentsC"));
        Tertiary.setCellValueFactory(new PropertyValueFactory<>("treatmentsD"));
        Sludge.setCellValueFactory(new PropertyValueFactory<>("treatmentsE"));
        TSS.setCellValueFactory(new PropertyValueFactory<>("TSS"));
        COD.setCellValueFactory(new PropertyValueFactory<>("COD"));
        BOD.setCellValueFactory(new PropertyValueFactory<>("BOD"));
        COST.setCellValueFactory(new PropertyValueFactory<>("cost"));
        ResultView.setItems(WastewaterCharacteristic.menu.getResultsTable());

    }

    @FXML
    private void sortByCost(){

    }

    @FXML
    private void ComboBoxOnActionListener(){

    }





}
