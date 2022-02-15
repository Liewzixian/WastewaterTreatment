package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class ManageModel implements Initializable {

    private final ObservableList<Models> detailss = FXCollections.observableArrayList();
    File inputFile = new File("src/main/resources/com/Treatment/output.txt");
    File tempFile = new File("src/main/resources/com/Treatment/output1.txt");

    @FXML
    private Button BackButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button ModifyButton;
    @FXML
    private TableView<Models> TableView;
    @FXML
    private TableColumn<Models, String> NameColumn;

    @FXML
    private TextField SearchBar;

    @FXML
    private Button Search;

    @FXML
    private TableColumn<Models, String> StageColumn;

    @FXML
    private TableColumn<Models, String> areaColumn;

    @FXML
    private TableColumn<Models, String> bodColumn;

    @FXML
    private TableColumn<Models, String> codColumn;

    @FXML
    private TableColumn<Models, String> energyColumn;

    @FXML
    private TableColumn<Models, String> tssColumn;


    public void readFile() throws Exception {
        Collection<Models> list = Files.readAllLines(Paths.get(String.valueOf(inputFile)))
                .stream()
                .map(line -> {
                    String[] details = line.split(",");
                    Models md = new Models();
                    md.setStage(details[0]);
                    md.setName(details[1]);
                    md.setCOD(details[2]);
                    md.setBOD(details[3]);
                    md.setTSS(details[4]);
                    md.setArea(details[5]);
                    md.setEnergy(details[6]);
                    return md;
                })
                .collect(Collectors.toList());
        ObservableList<Models> details = FXCollections.observableArrayList(list);

        // TableView.getColumns().addAll(StageColumn, NameColumn, codColumn, bodColumn, tssColumn);
/*
        StageColumn.setCellValueFactory(data -> data.getValue().stageProperty());
        NameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        codColumn.setCellValueFactory(data -> data.getValue().codProperty());
        bodColumn.setCellValueFactory(data -> data.getValue().bodProperty());
        tssColumn.setCellValueFactory(data -> data.getValue().tssProperty());
        areaColumn.setCellValueFactory(data -> data.getValue().areaProperty());
        energyColumn.setCellValueFactory(data -> data.getValue().energyProperty());
*/
        
        StageColumn.setCellValueFactory(new PropertyValueFactory<>("stage"));
        StageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        NameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
         codColumn.setCellValueFactory(new PropertyValueFactory<>("COD"));
        codColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        codColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Models, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Models, String> t) {
                        ((Models) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCOD(t.getNewValue());
                    }
                }
        );
        bodColumn.setCellValueFactory(new PropertyValueFactory<>("BOD"));
        bodColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bodColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Models, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Models, String> t) {
                        ((Models) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setBOD(t.getNewValue());
                    }
                }
        );
        tssColumn.setCellValueFactory(new PropertyValueFactory<>("TSS"));
        tssColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tssColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Models, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Models, String> t) {
                        ((Models) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setTSS(t.getNewValue());
                    }
                }
        );
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        areaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        energyColumn.setCellValueFactory(new PropertyValueFactory<>("energy"));
        energyColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        TableView.setItems(details);
        detailss.addAll(details);
        TableView.setEditable(true);
    }

    public void searCh() {
        FilteredList<Models> filteredData = new FilteredList<>(detailss, e -> true);
        SearchBar.setOnKeyReleased(e -> {
            SearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Models>) models -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (models.toString().contains(newValue)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Models> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(TableView.comparatorProperty());
            TableView.setItems(sortedData);
        });


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //searCh();
        try {
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void BackButtonOnAction() {
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
    void ModifyButtonOnAction() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        ObservableList<Models> Md = TableView.getItems();

        String[] words = Md.toString().split(", ");
        for (String word: words) {
            writer.write(word.replace("[","").replace("]", ""));
            writer.newLine();
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }


    @FXML
    void deleteButtonOnAction(ActionEvent event) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        Models Md = TableView.getSelectionModel().getSelectedItem();
        String currentLine ;

        while ((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine;

            if(trimmedLine.equals(Md.toString())) {
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);

        TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItems());
    }

}
