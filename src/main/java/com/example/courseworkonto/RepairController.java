package com.example.courseworkonto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class RepairController {

    @FXML
    private ListView<String> lv_repairs;

    @FXML
    protected void AddNewRepair() throws IOException {
        Stage current_stage = (Stage) lv_repairs.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("choose_fault.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) lv_repairs.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("knowledge-editor.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }


}
