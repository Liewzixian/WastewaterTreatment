package com.example.demo1;

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

import java.io.IOException;
import java.util.ArrayList;

import static com.example.demo1.LoginController.menu;


public class SelectionController {

    ArrayList<Print> SelectedList = new ArrayList<>();
    ObservableList<Print>Unselected= menu.getSelectionTable();


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
    private TableColumn<Print,Integer> UnselectedModel;

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
        Print selection= UnselectedTable.getSelectionModel().getSelectedItem();
        Print theSelected=new Print(selection.getStage(),selection.getTreatments());
        SelectedList.add(theSelected);
        SelectedTable.getItems().add(theSelected);
        UnselectedTable.getItems().remove(selection);
    }

    @FXML
    private void initialize (){
        menu.showAllTreatments();
        UnselectedStage.setCellValueFactory(new PropertyValueFactory<>("stage"));
        UnselectedModel.setCellValueFactory(new PropertyValueFactory<>("treatments"));
        SelectedStage.setCellValueFactory(new PropertyValueFactory<>("stage"));
        SelectedModel.setCellValueFactory(new PropertyValueFactory<>("treatments"));
        UnselectedTable.setItems(menu.getSelectionTable());
        Search();

    }
    @FXML
    public void Search() {
        FilteredList<Print> filteredData = new FilteredList<>(Unselected, b -> true);
        UnselectedTextField.setOnKeyReleased(e->{
        UnselectedTextField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(Unselected -> {
            if (newValue.isEmpty() || newValue.isBlank() || newValue.isBlank()) {
                return true;
            }
            String Keyword = newValue.toLowerCase();
            return Unselected.getTreatments().toLowerCase().contains(Keyword);
        }));
        SortedList<Print> sortedList= new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(UnselectedTable.comparatorProperty());
        UnselectedTable.setItems(sortedList);
    });
    }

    public ArrayList<ArrayList<Tech>> getChoice(){

        ArrayList<ArrayList<Tech>> choice = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            choice.add(new ArrayList<>());
        }

        for(Print list: SelectedList){
            int i = 0;
            for(Tech find: menu.fullList.get(list.stage - 1)){
                if(list.treatments.equalsIgnoreCase(find.getName())) {
                    choice.get(list.stage - 1).add(menu.fullList.get(list.stage - 1).get(i));
                    break;
                }
                i++;
            }
        }
        return choice;
    }

    public boolean[] stageFlag(){
        boolean [] flag = new boolean[5];
        int [] count = new int[5];

        for(Print list: SelectedList){
            count[list.stage-1]++;
        }

        for(int i = 0; i < 5; i++){
            if(count[i]>0)
                flag[i] = true;
        }
        return flag;
    }

}
