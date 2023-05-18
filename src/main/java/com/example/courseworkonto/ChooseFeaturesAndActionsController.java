package com.example.courseworkonto;

import com.example.courseworkonto.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseFeaturesAndActionsController implements Initializable {


    @FXML
    private TextArea features_values;

    @FXML
    private TextArea actions_repair;

    private Integer id_fault;

    @FXML
    protected void RefreshFeatures() throws SQLException {
        Stage current_stage = (Stage) features_values.getScene().getWindow();
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
        features_values.setText(str);
    }

    @FXML
    protected void AddRepair() throws SQLException, IOException {
        String[] items = actions_repair.getText().split("\n");
        ArrayList<String> result;
        ArrayList<String> ids = new ArrayList<>();
        boolean isUnique = true;
        for (int i = 0; i < items.length; i++)
        {
            result = ActionModel.SelectActionByName(items[i]);
            if (result.size() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка в '"+items[i]+"'");
                alert.show();
                isUnique = false;
                break;
            }
            ids.add(result.get(0));
        }
        if (isUnique)
        {

            int index = RepairModel.AddRepair(id_fault, actions_repair.getText());
            String[] newItems = features_values.getText().split("\n");
            ArrayList<String> newResult;
            ArrayList<String> newids = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            for (int i = 0; i < newItems.length; i++)
            {
                newResult = AddFeatureModel.SelectFeaturesByName(newItems[i].split("=")[0].trim());
                newids.add(newResult.get(3));
                names.add(newItems[i].split("=")[1].trim());
            }
            FeatureRepairsModel.AddNewRepairFeature(Integer.toString(index), newids, names);
            Stage current_stage = (Stage) actions_repair.getScene().getWindow();
            Parent home_page = FXMLLoader.load(getClass().getResource("repair.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
