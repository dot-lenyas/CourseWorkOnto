package com.example.courseworkonto;

import com.example.courseworkonto.model.DetailsModel;
import com.example.courseworkonto.model.FaultModel;
import com.example.courseworkonto.model.FeatureDetailsModel;
import com.example.courseworkonto.model.FeatureFaultsModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FaultViewController implements Initializable {

    @FXML
    private TextArea features_name;

    @FXML
    private TextField name_fault;

    @FXML
    private ListView<String> lv_faults;

    @FXML
    protected void AddFault() throws SQLException, IOException {
        String[] items = features_name.getText().split("\n");
        ArrayList<String> result;
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        boolean isUnique = true;
        for (int i = 0; i < items.length; i++)
        {
            String[] item_name = items[i].split("=");
            result = DetailsModel.SelectFeatureByName(item_name[0].trim());
            if (result.size() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка в '"+items[i].split("=")+"'");
                alert.show();
                isUnique = false;
                break;
            }
            ids.add(result.get(0));
            values.add(item_name[1].trim());
        }
        if (isUnique)
        {
            int index = FaultModel.AddFault(name_fault.getText());
            FeatureFaultsModel.AddNewFaultsFeature(Integer.toString(index), ids, values);
            Stage current_stage = (Stage) name_fault.getScene().getWindow();
            Parent home_page = FXMLLoader.load(getClass().getResource("fault_view.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }

    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) features_name.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("knowledge-editor.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> result;
        try {
            result = FaultModel.SelectAllFaults();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lv_faults.getItems().addAll(result);

        lv_faults.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Stage current_stage = (Stage) lv_faults.getScene().getWindow();
                Parent home_page = null;
                try {
                    home_page = FXMLLoader.load(getClass().getResource("info-about-fault.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene home_scene = new Scene(home_page);
                current_stage.setUserData(lv_faults.getSelectionModel().getSelectedItem());
                current_stage.setScene(home_scene);
                current_stage.show();
            }
        });
    }
}
