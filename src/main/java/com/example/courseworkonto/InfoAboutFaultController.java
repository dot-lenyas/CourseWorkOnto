package com.example.courseworkonto;

import com.example.courseworkonto.model.DetailsModel;
import com.example.courseworkonto.model.FaultModel;
import com.example.courseworkonto.model.FeatureDetailsModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoAboutFaultController {

    @FXML
    private Label name;

    @FXML
    private ListView<String> lv_faults;


    @FXML
    protected void RefreshPage() throws SQLException {
        Stage current_stage = (Stage) name.getScene().getWindow();
        Object obj = current_stage.getUserData();
        String fault_name = (String) obj;
        ArrayList<String> result = FaultModel.SelectFaultByName(fault_name);
        name.setText("Имя: " + result.get(1));
        ArrayList<String> names = FeatureDetailsModel.SelectFeaturesAndValuesName(result.get(0));
        lv_faults.getItems().addAll(names);
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) lv_faults.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("fault_view.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }
}
