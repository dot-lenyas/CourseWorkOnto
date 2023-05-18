package com.example.courseworkonto;

import com.example.courseworkonto.model.FaultModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseFaultController implements Initializable {

    @FXML
    private ChoiceBox<String> cb_faults;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> names;
        try {
            names = FaultModel.SelectAllFaults();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cb_faults.getItems().addAll(names);
        cb_faults.setValue(names.get(0));
    }

    @FXML
    protected void ContinueAddingRepair() throws IOException {
        Stage current_stage = (Stage) cb_faults.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("choose-features-and-actions.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setUserData(cb_faults.getValue());
        current_stage.setScene(home_scene);
        current_stage.show();
    }
}
