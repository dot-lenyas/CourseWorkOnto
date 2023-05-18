package com.example.courseworkonto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void editorRedirect(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Stage current_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("knowledge-editor.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }

    @FXML
    protected void insertRedirect(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Stage current_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("choose-fault-solution.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }
}