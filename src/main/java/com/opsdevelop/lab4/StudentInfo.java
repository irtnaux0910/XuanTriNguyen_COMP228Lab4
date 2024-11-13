package com.opsdevelop.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.io.IOException;
import javafx.scene.control.Button;

import javax.swing.*;

public class StudentInfo extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentInfo.class.getResource("hello-view.fxml"));

        stage.setTitle("Student Information");
        Image icon = new Image("file:D:\\Centennial\\Semester 3\\COMP-228_Java Programming\\Assignment\\Lab 4\\Lab4\\src\\main\\java\\assets\\logo.png");
        stage.getIcons().add(icon);

        BorderPane root = new BorderPane();

        //declare Grid Pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setHgap(5);
        pane.setVgap(5);

        //Add the text fields
        TextField nameField = new TextField();
        pane.add(new Label("Name:"), 0, 0);
        pane.add(new Label("                                 "), 1, 0); //whitespace to provide space between the labels and text fields
        pane.add(nameField, 2, 0);

        TextField addressField = new TextField();
        pane.add(new Label("Address:"), 0, 1);
        pane.add(new Label("                                 "), 1, 1);
        pane.add(addressField, 2, 1);

        TextField provinceField = new TextField();
        pane.add(new Label("Province:"), 0, 2);
        pane.add(new Label("                                 "), 1, 2);
        pane.add(provinceField, 2, 2);

        TextField cityField = new TextField();
        pane.add(new Label("City:"), 0, 3);
        pane.add(new Label("                                 "), 1, 3);
        pane.add(cityField, 2, 3);

        TextField postalCodeField = new TextField();
        pane.add(new Label("Postal Code:"), 0, 4);
        pane.add(new Label("                                 "), 1, 4);
        pane.add(postalCodeField, 2, 4);

        TextField phoneNumberField = new TextField();
        pane.add(new Label("Phone Number:"), 0, 5);
        pane.add(new Label("                                 "), 1, 5);
        pane.add(phoneNumberField, 2, 5);

        TextField emailField = new TextField();
        pane.add(new Label("Email:"), 0, 6);
        pane.add(new Label("                                 "), 1, 6);
        pane.add(emailField, 2, 6);

        //Add the checkboxes
        CheckBox studentCouncil = new CheckBox("Student Council");
        pane.add(studentCouncil, 3, 1);

        CheckBox volunteerWork = new CheckBox("Volunteer Work");
        pane.add(volunteerWork, 3, 5);

        //Add the radio buttons
        RadioButton csRButton = new RadioButton("Computer Science");
        pane.add(csRButton, 5, 0);
        RadioButton businessRButton = new RadioButton("Business");
        pane.add(businessRButton, 6, 0);

        //Create a ToggleGroup for the radio buttons so that only one can be selected
        ToggleGroup majorGroup = new ToggleGroup();
        csRButton.setToggleGroup(majorGroup);
        businessRButton.setToggleGroup(majorGroup);

        //Add the combo boxes
        ComboBox<String> coursesBox = new ComboBox<>();
        ListView<String> selectedCoursesList = new ListView<>();

        coursesBox.prefWidthProperty().bind(selectedCoursesList.widthProperty());
        coursesBox.setPrefHeight(50);
        selectedCoursesList.setPrefHeight(100);

        //Create HBox for the radio buttons
        HBox radioButtonBox = new HBox(csRButton, businessRButton);
        radioButtonBox.setSpacing(10);
        radioButtonBox.setPadding(new Insets(20, 0, 0, 0));

        //Create VBox for the combo box and list view
        VBox comboBoxListViewBox = new VBox(coursesBox, selectedCoursesList);
        comboBoxListViewBox.setSpacing(5);

        //Add the HBox and VBox to a VBox
        VBox majorBox = new VBox(radioButtonBox, comboBoxListViewBox);
        majorBox.setSpacing(30);

        //Add the VBox majorBox to the right of the BorderPane
        root.setRight(majorBox);

        //Add the Display button above the text area
        Button displayButton = new Button("Display");
        displayButton.setPrefWidth(100);

        //Create the TextArea
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(200);

        //Create a VBox to hold the button HBox and the text area
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(displayButton, textArea);

        //Add the TextArea to the bottom of the BorderPane
        root.setBottom(vbox);

        //Set GridPane to the center of BorderPane
        root.setCenter(pane);



        //Add event handlers for the radio buttons
        csRButton.setOnAction(e -> {
            coursesBox.getItems().clear();
            coursesBox.getItems().addAll("Java","C++","SQL", "Python", "C#", "Web Development", "Data Structures", "Algorithms", "Software Engineering");
        });

        businessRButton.setOnAction(e -> {
            coursesBox.getItems().clear();
            coursesBox.getItems().addAll("Entrepreneurship", "Accounting", "Business Management", "Marketing", "Finance", "Economics", "Business Law", "Business Ethics", "Business Communication");

        });

        //Add event handler for the combo box
        coursesBox.setOnAction(e -> {
            String selectedCourse = coursesBox.getSelectionModel().getSelectedItem();
            if (selectedCourse != null && !selectedCoursesList.getItems().contains(selectedCourse)) {
                selectedCoursesList.getItems().add(selectedCourse);
            }
        });

        //Add event handler for the Display button
        displayButton.setOnAction(e -> {
            if (nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
                    provinceField.getText().isEmpty() || cityField.getText().isEmpty() ||
                    postalCodeField.getText().isEmpty() || phoneNumberField.getText().isEmpty() ||
                    emailField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"PLEASE FILL IN ALL THE FIELDS");
            }
            //if a course has not been selected, display message
            else if ( selectedCoursesList.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(null,"PLEASE SELECT A COURSE");
            }
            else if (!phoneNumberField.getText().matches("[0-9()\\- ]*"))
            {
                JOptionPane.showMessageDialog(null,"PLEASE ENTER A VALID NUMBER");
            }
            else if (!emailField.getText().contains("@") || !emailField.getText().contains(".") || emailField.getText().endsWith(".")) {
                JOptionPane.showMessageDialog(null,"Email address should be valid. A valid email address should have a domain");
            }
            else
            {
                String studentInfo = "Student Info " + textArea.getText().split("Student Info ").length + "\n";
                studentInfo += nameField.getText() + ",  "
                        + addressField.getText() + ",  "
                        + provinceField.getText() + ",  "
                        + cityField.getText() + ",  "
                        + postalCodeField.getText() + ",  "
                        + phoneNumberField.getText() + ",  "
                        +   emailField.getText() + "\n"
                        + "Major: " + ((RadioButton) majorGroup.getSelectedToggle()).getText() + "\n"
                        + "Student Council: " + (studentCouncil.isSelected() ? "Yes" : "No") + "\n"
                        + "Volunteer Work: " + (volunteerWork.isSelected() ? "Yes" : "No");

                //Append each course to the studentInfo string
                studentInfo += "\nCourses:\n";
                for (String course : selectedCoursesList.getItems()) {
                    studentInfo += course + "\n";
                }
                textArea.appendText(studentInfo + "\n");

            }});

        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
            launch();
        };
    }
