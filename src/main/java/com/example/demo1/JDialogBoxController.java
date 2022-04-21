package com.example.demo1;

import javafx.fxml.FXML;

public class JDialogBoxController{
    @FXML
    protected void okButtonOnAction() {
       WastewaterCharacteristic close = new WastewaterCharacteristic();
       close.stage.close();
    }
}
