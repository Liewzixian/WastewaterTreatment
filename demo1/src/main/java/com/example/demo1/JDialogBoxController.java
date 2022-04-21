package com.example.demo1;

import javafx.fxml.FXML;

/**
 * This class show a dialog box to display the standard info in the wastewater characteristic
 */
public class JDialogBoxController{
    /**
     * This method will close the pop-up dialog box
     */
    @FXML
    protected void okButtonOnAction() {
       WastewaterCharacteristic close = new WastewaterCharacteristic();
       close.stage.close();
    }
}
