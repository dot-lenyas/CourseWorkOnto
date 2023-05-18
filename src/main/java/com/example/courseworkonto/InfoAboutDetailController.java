package com.example.courseworkonto;

import com.example.courseworkonto.model.DetailsModel;
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

public class InfoAboutDetailController {
    @FXML
    private Label name;

    @FXML
    private ListView<String> lv_details;

    @FXML
    protected void RefreshPage() throws SQLException {
        Stage current_stage = (Stage) name.getScene().getWindow();
        Object obj = current_stage.getUserData();
        String detail_name = (String) obj;
        ArrayList<String> result = DetailsModel.SelectDetailByName(detail_name);
        name.setText("Имя: " + result.get(1));
        ArrayList<String> names = FeatureDetailsModel.SelectFeaturesName(result.get(0));
        lv_details.getItems().addAll(names);
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) lv_details.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("details-view.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }
}
