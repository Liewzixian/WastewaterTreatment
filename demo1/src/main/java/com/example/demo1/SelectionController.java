package com.example.demo1;

import com.example.demo1.dataclasses.Selection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.demo1.LoginController.menu;


public class SelectionController {
    SharedData sharedData;
    ObservableList<Selection> Unselected;
    ObservableList<Selection> Selected;
    private final LinkedHashMap<String,LinkedHashMap<String,Selection>> tempList;

    static Stage stage = new Stage();
    boolean ModelValidation;

    @FXML
    private TextField UnselectedTextField;

    @FXML
    private TextField SelectedTextField;

    @FXML
    private TableColumn<Selection, String> UnselectedStage;

    @FXML
    private TableColumn<Selection, Integer> UnselectedModel;

    @FXML
    private TableColumn<Selection, String> SelectedStage;

    @FXML
    private TableColumn<Selection, String> SelectedModel;

    @FXML
    private TableView<Selection> UnselectedTable;
    @FXML
    private TableView<Selection> SelectedTable;

    public SelectionController() {

        sharedData = menu.sharedData;

        String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
        tempList = new LinkedHashMap<>();

        for(String loop : treatments)
            tempList.computeIfAbsent(loop, k -> new LinkedHashMap<>());

        Unselected = FXCollections.observableList(sharedData.getUnselected());
        Selected = FXCollections.observableList(sharedData.getSelected());
    }

    @FXML
    protected void backButtonOnAction() {
        ModelValidation=true;

        /*boolean[] flag = stageFlag();
        for (int i = 0; i < 5; i++)
            ModelValidation = ModelValidation && flag[i];*/

        SoundEffect sound = new SoundEffect();
        if (ModelValidation) {
            sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
            FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("Menu-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 585, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Login.window.setScene(scene);
            SetSceneOnCentral(Login.window);
        }
        else {
            sound.playSound("src/main/resources/com/SoundEffect/error.wav");
            FXMLLoader fxmlLoader = new FXMLLoader(WastewaterCharacteristic.class.getResource("SelectionAlert-view.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 359, 180);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.show();
            SetSceneOnCentral(stage);
        }
    }

    public void Search()  {

        FilteredList<Selection> filteredData = new FilteredList<>(Unselected, k -> true);
        UnselectedTextField.setOnKeyReleased(e -> {
            UnselectedTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Unselected -> {
                if (newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }
                else {
                    String Keyword = newValue.toLowerCase();
                    return Unselected.getTreatments().toLowerCase().contains(Keyword);
                }
            }));
            SortedList<Selection> sortedList = new SortedList<>(filteredData);
            sortedList.comparatorProperty().bind(UnselectedTable.comparatorProperty());
            UnselectedTable.setItems(sortedList);
        });

        FilteredList<Selection> filteredData1 = new FilteredList<>(Selected, k -> true);
        SelectedTextField.setOnKeyReleased(f -> {
            SelectedTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData1.setPredicate(selected -> {
                if (newValue.isEmpty() || newValue.isBlank()) {
                    SelectedTable.setItems(Selected);
                    return true;
                }
                else {
                    String Keyword = newValue.toLowerCase();
                    return selected.getTreatments().toLowerCase().contains(Keyword);
                }
            }));
            SortedList<Selection> sortedList1 = new SortedList<>(filteredData1);
            sortedList1.comparatorProperty().bind(SelectedTable.comparatorProperty());
            SelectedTable.setItems(sortedList1);
        });
    }

    @FXML
    protected void selectAllButtonOnAction() {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");

        ObservableList<Selection> selection = UnselectedTable.getItems();

        for(Map.Entry<String, LinkedHashMap<String, Selection>> loop : tempList.entrySet())
            tempList.get(loop.getKey()).clear();

        for(Selection loop : Selected)
            tempList.get(loop.stage).put(loop.treatments,loop);

        for(Selection loop : selection)
            tempList.get(loop.stage).put(loop.treatments,loop);

        Selected.clear();

        for(Map.Entry<String, LinkedHashMap<String, Selection>> loop : tempList.entrySet())
            for (Map.Entry<String, Selection> print : loop.getValue().entrySet())
                Selected.add(print.getValue());
        remove();
        UnselectedTable.getItems().clear();
    }

    public void remove() {
        UnselectedTextField.clear();
        SelectedTextField.clear();
    }

    @FXML
    private void initialize(){
        UnselectedStage.setCellValueFactory(new PropertyValueFactory<>("stage"));
        UnselectedModel.setCellValueFactory(new PropertyValueFactory<>("treatments"));
        SelectedStage.setCellValueFactory(new PropertyValueFactory<>("stage"));
        SelectedModel.setCellValueFactory(new PropertyValueFactory<>("treatments"));
        UnselectedTable.setItems(Unselected);
        SelectedTable.setItems(Selected);
        SelectOnDoubleClick();
        DeleteOnDoubleClick();
        Search();
    }

    public boolean[] stageFlag(){

        String[] treatments = {"PRELIMINARY","CHEMICAL","BIOLOGICAL","TERTIARY","SLUDGE"};
        boolean [] flag = new boolean[5];

        for(Selection list: Selected){
            flag[Arrays.asList(treatments).indexOf(list.stage)] = true;
        }
        return flag;
    }

    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    @FXML
    protected void SelectOnDoubleClick(){
        UnselectedTable.setRowFactory( tv -> {
            TableRow<Selection> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SoundEffect sound = new SoundEffect();
                    sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
                    Selection selection = UnselectedTable.getSelectionModel().getSelectedItem();
                    Unselected.remove(selection);

                    for(Map.Entry<String, LinkedHashMap<String, Selection>> loop : tempList.entrySet())
                        tempList.get(loop.getKey()).clear();

                    for(Selection loop : Selected)
                        tempList.get(loop.stage).put(loop.treatments,loop);
                    tempList.get(selection.stage).put(selection.treatments,selection);

                    Selected.clear();

                    for(Map.Entry<String, LinkedHashMap<String, Selection>> loop : tempList.entrySet())
                        for (Map.Entry<String, Selection> print : loop.getValue().entrySet())
                            Selected.add(print.getValue());

                    remove();
                }
            });
            return row ;
        });
    }

    @FXML
    protected  void DeleteOnDoubleClick(){
        SelectedTable.setRowFactory( tv -> {
            TableRow<Selection> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SoundEffect sound = new SoundEffect();
                    sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
                    Selection selection = SelectedTable.getSelectionModel().getSelectedItem();
                    Selected.remove(selection);
                    Unselected.add(selection);
                    UnselectedTable.refresh();

                    remove();

                }
            });
            return row ;
        });
    }

    @FXML
    protected void DeleteAllOnAction(){
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");

        ObservableList<Selection> selection = SelectedTable.getItems();

        for(Map.Entry<String, LinkedHashMap<String, Selection>> loop : tempList.entrySet())
            tempList.get(loop.getKey()).clear();

        for(Selection loop : Unselected)
            tempList.get(loop.stage).put(loop.treatments,loop);

        for(Selection loop : Selected)
            tempList.get(loop.stage).put(loop.treatments,loop);

        for(Selection loop : selection)
            tempList.get(loop.stage).put(loop.treatments,loop);

        Selected.clear();
        Unselected.clear();

        for(Map.Entry<String, LinkedHashMap<String, Selection>> loop : tempList.entrySet())
            for (Map.Entry<String, Selection> print : loop.getValue().entrySet())
                Unselected.add(print.getValue());

        remove();
        UnselectedTable.refresh();
        SelectedTable.refresh();
    }
}