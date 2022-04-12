package com.example.demo1;

import javafx.fxml.FXML;

import java.io.FileNotFoundException;

public class JDialogBoxController{
    @FXML
    protected void okButtonOnAction() throws FileNotFoundException {
       SelectionController close = new SelectionController();
       close.stage.close();
    }
}
