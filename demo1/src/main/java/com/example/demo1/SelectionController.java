package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.demo1.LoginController.menu;


public class SelectionController {

    static LinkedHashMap<String,LinkedHashMap<String,Tech>> choice = new LinkedHashMap<>();
    ArrayList<Print> SelectedList = new ArrayList<>(Arrays.asList());
    ObservableList<Print> Unselected = menu.getSelectionTable();
    ObservableList<Print> Selected = FXCollections.observableArrayList(SelectedList);

    static boolean sign= false;
    static Stage stage = new Stage();
    boolean ModelValidation;

    @FXML
    private Button selectButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField UnselectedTextField;

    @FXML
    private TextField SelectedTextField;

    @FXML
    private TableColumn<Print, String> UnselectedStage;

    @FXML
    private TableColumn<Print, Integer> UnselectedModel;

    @FXML
    private TableColumn<Print, String> SelectedStage;

    @FXML
    private TableColumn<Print, String> SelectedModel;

    @FXML
    private TableView<Print> UnselectedTable;

    @FXML
    private TableView<Print> SelectedTable;

    @FXML
    protected void backButtonOnAction() {
        ModelValidation=true;
        for (int i = 0; i < 5; i++) {
            boolean[] flag = stageFlag();
            ModelValidation = ModelValidation && flag[i];
        }
        if (ModelValidation) {
            sign=true; // this is to show user wan to customize the models that be taken into comparison
            FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("Menu-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 585, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Login.window.setScene(scene);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("SelectionAlert-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 359, 180);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.show();
        }

    }

    public void Search()  {

           FilteredList<Print> filteredData = new FilteredList<>(Unselected, b -> true);
           UnselectedTextField.setOnKeyReleased(e -> {
               UnselectedTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Unselected -> {
                   if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                       return true;
                   } else {
                       String Keyword = newValue.toLowerCase();
                       return Unselected.getTreatments().toLowerCase().contains(Keyword);
                   }
               }));
               SortedList<Print> sortedList = new SortedList<>(filteredData);
               sortedList.comparatorProperty().bind(UnselectedTable.comparatorProperty());
               UnselectedTable.setItems(sortedList);

           });
           SelectedTable.setItems(Selected);
            FilteredList<Print> filteredData1 = new FilteredList<>(Selected, a -> true);
            SelectedTextField.setOnKeyReleased(f -> {
                SelectedTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData1.setPredicate(selected -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                       // SelectedTable.setItems(Selected);
                        return true;
                    } else {
                        String Keyword = newValue.toLowerCase();
                        return selected.getTreatments().toLowerCase().contains(Keyword);
                    }
                }));
                    SortedList<Print> sortedList1 = new SortedList<>(filteredData1);
                    sortedList1.comparatorProperty().bind(SelectedTable.comparatorProperty());
                    ObservableList<Print> Selected2 = FXCollections.observableArrayList(sortedList1);
                    Selected = Selected2;
                    SelectedTable.setItems(Selected2);

            });
        }

    @FXML
    protected void selectButtonOnAction() {
        Print selection = UnselectedTable.getSelectionModel().getSelectedItem();
        Print theSelected = new Print(selection.getStage(), selection.getTreatments());
        SelectedList.add(theSelected);
        SelectedTable.getItems().add(theSelected);
        remove();

        for(Map.Entry<String, LinkedHashMap<String, Tech>> loop : menu.fullList.entrySet())
            choice.put(loop.getKey(),new LinkedHashMap<>());

        for(Print print : SelectedList)
            choice.get(print.stage).put(print.treatments,menu.fullList.get(print.stage).get(print.treatments));

        for (Map.Entry<String, LinkedHashMap<String, Tech>> loop : choice.entrySet())
            for (Map.Entry<String, Tech> print : loop.getValue().entrySet())
                System.out.println(loop.getKey() + " " + print.getKey());
    }

    public void remove() {
        UnselectedTable.setItems(Unselected);
        UnselectedTextField.clear();
        SelectedTextField.clear();
        UnselectedTable.getItems().remove(UnselectedTable.getSelectionModel().getSelectedItem());
    }

    public void update(){
        SelectedTable.setItems(Selected);
}

    @FXML
    private void initialize (){
        menu.showAllTreatments();
        UnselectedStage.setCellValueFactory(new PropertyValueFactory<>("stage"));
        UnselectedModel.setCellValueFactory(new PropertyValueFactory<>("treatments"));
        SelectedStage.setCellValueFactory(new PropertyValueFactory<>("stage"));
        SelectedModel.setCellValueFactory(new PropertyValueFactory<>("treatments"));
        UnselectedTable.setItems(Unselected);
        Search();

    }

    public boolean[] stageFlag(){

        String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
        boolean [] flag = new boolean[5];

        for(Print list: SelectedList){
            flag[Arrays.asList(treatments).indexOf(list.stage)] = true;
        }
        return flag;
    }
}

