package com.example.demo1;

import javafx.fxml.FXML;

public class JDialogBoxController{
    @FXML
    protected void okButtonOnAction() {
       SelectionController close = new SelectionController();
       close.stage.close();
    }
}
