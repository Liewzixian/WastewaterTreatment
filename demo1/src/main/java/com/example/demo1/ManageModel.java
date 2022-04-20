package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.example.demo1.LoginController.menu;
public class ManageModel implements Initializable {

    private final ObservableList<Models> detailss = FXCollections.observableArrayList();
    File inputFile = new File("src/main/resources/com/Treatment/output.txt");
    File tempFile = new File("src/main/resources/com/Treatment/output1.txt");
    boolean deleteValidation;
    boolean renameValidation;

    @FXML
    private TableView<Models> TableView;
    @FXML
    private TableColumn<Models, String> NameColumn;

    @FXML
    private TextField SearchBar;

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

    @FXML
    private TableColumn<Models, String> CostColumn;


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
                    md.setCost(details[7]);
                    return md;
                })
                .collect(Collectors.toList());
        ObservableList<Models> details = FXCollections.observableArrayList(list);

        // TableView.getColumns().addAll(StageColumn, NameColumn, codColumn, bodColumn, tssColumn);

        StageColumn.setCellValueFactory(new PropertyValueFactory<>("stage"));
        StageColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        NameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        codColumn.setCellValueFactory(new PropertyValueFactory<>("COD"));
        codColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        codColumn.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setCOD(t.getNewValue())
        );
        bodColumn.setCellValueFactory(new PropertyValueFactory<>("BOD"));
        bodColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bodColumn.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setBOD(t.getNewValue())
        );
        tssColumn.setCellValueFactory(new PropertyValueFactory<>("TSS"));
        tssColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tssColumn.setOnEditCommit(
                t -> t.getTableView().getItems().get(
                        t.getTablePosition().getRow()).setTSS(t.getNewValue())
        );
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        areaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        energyColumn.setCellValueFactory(new PropertyValueFactory<>("energy"));
        energyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CostColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        CostColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        TableView.setItems(details);
        detailss.addAll(details);
        TableView.setEditable(true);
    }

    public void searCh() {
        FilteredList<Models> filteredData = new FilteredList<>(detailss, e -> true);
        SearchBar.setOnKeyReleased(e -> {
            SearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredData.setPredicate((Predicate<? super Models>) models -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return models.toString().contains(newValue);
            }));

            SortedList<Models> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(TableView.comparatorProperty());
            TableView.setItems(sortedData);
        });


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searCh();
        try {
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    void BackButtonOnAction() {
        SoundEffect sound = new SoundEffect();
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

    @FXML
    void ModifyButtonOnAction() throws Exception {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
        ObservableList<Models> Md = TableView.getItems();

        String[] words = Md.toString().split(", ");
        for (String word: words) {
            writer.write(word.replace("[","").replace("]", ""));
            writer.newLine();
        }
        writer.close();
        menu.sharedData.reloadData();
    }


    @FXML
    void deleteButtonOnAction() throws IOException {
        SoundEffect sound = new SoundEffect();
        sound.playSound("src/main/resources/com/SoundEffect/clicksound.wav");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        Models Md = TableView.getSelectionModel().getSelectedItem();
        String currentLine ;

        while ((currentLine = reader.readLine()) != null) {

            if(currentLine.equals(Md.toString())) {
               continue;
            }else {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
        TableView.getItems().remove(Md);
    }
    @FXML
    protected void SetSceneOnCentral(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}