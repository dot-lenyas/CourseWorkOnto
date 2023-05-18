package com.example.courseworkonto;

import com.example.courseworkonto.model.FaultModel;
import com.example.courseworkonto.model.FeatureDetailsModel;
import com.example.courseworkonto.model.RepairModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SetFeaturesValuesController {

    private Integer id_fault;

    @FXML
    private TextArea features_name;

    @FXML
    protected void RefreshFeatures() throws SQLException {
        Stage current_stage = (Stage) features_name.getScene().getWindow();
        Object obj = current_stage.getUserData();
        String result = (String) obj;
        ArrayList<String> id = FaultModel.SelectFaultByName(result);
        ArrayList<String> features = FeatureDetailsModel.SelectFeaturesAndValuesName(id.get(0));
        String str = "";
        for (int i = 0; i < features.size(); i++)
        {
            str += features.get(i) + "\n";
        }
        id_fault = Integer.parseInt(id.get(0));
        features_name.setText(str);
    }

    @FXML
    protected void FinishProgramm() throws SQLException, IOException {
        Stage current_stage = (Stage) features_name.getScene().getWindow();
        Object obj = current_stage.getUserData();
        String result = (String) obj;
        ArrayList<String> res = FaultModel.SelectFaultByName(result);
        ArrayList<String> id = RepairModel.SelectRepairByFaultId(Integer.parseInt(res.get(0)));
        if (id.size() == 1)
        {
            System.out.println(id.size());
            Parent home_page = FXMLLoader.load(getClass().getResource("finished-window.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setUserData(id.get(0));
            current_stage.setScene(home_scene);
            current_stage.show();
        }
        else if (id.size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }


}
