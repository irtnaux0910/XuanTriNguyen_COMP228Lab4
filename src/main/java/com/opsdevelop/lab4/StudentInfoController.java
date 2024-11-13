package com.opsdevelop.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentInfoController {
    @FXML
    private Label studentInfoText;

    @FXML
    protected void onHelloButtonClick() {
        studentInfoText.setText("Welcome to Student Information Application!");
    }
}