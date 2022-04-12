package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo1.LoginController.menu;

public class DisplayResult {
    static Stage stage = new Stage();
    ObservableList<String> Preference = FXCollections.observableArrayList("Best Overall","Cost Effectiveness ");

    static Print rowData;

    @FXML
    protected void BackButtonOnAction(){
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("WaterChar-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 595, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login.window.setScene(scene);
        SetSceneOnCentral(Login.window);
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
        ResultView.setItems(menu.getResultsTable());
        ClickListener();

    }

    @FXML
    private void sortByCost(){

    }

    @FXML
    private void ComboBoxOnActionListener(){

    }

    @FXML
    private void ClickListener(){
        ResultView.setRowFactory( tv -> {
            TableRow<Print> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SoundEffect sound = new SoundEffect();
                    sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
                    rowData = row.getItem();
                    DedicatedWindow();
                }
            });
            return row ;
        });

    }

    @FXML
    private void DedicatedWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("CombinationProcess-View.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 825, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
        SetSceneOnCentral(stage);
    }

    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }


}
