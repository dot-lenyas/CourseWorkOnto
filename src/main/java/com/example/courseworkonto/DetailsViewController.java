package com.example.courseworkonto;

import com.example.courseworkonto.model.DetailsModel;
import com.example.courseworkonto.model.FeatureDetailsModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetailsViewController implements Initializable {

    @FXML
    private ListView<String> lv_details;

    @FXML
    private TextField name_detail;

    @FXML
    private TextArea features_name;

    @FXML
    protected void AddDetail() throws SQLException, IOException {
        String[] items = features_name.getText().split("\n");
        ArrayList<String> result;
        ArrayList<String> ids = new ArrayList<>();
        boolean isUnique = true;
        for (int i = 0; i < items.length; i++)
        {
            result = DetailsModel.SelectFeatureByName(items[i]);
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
            int index = DetailsModel.AddDetail(name_detail.getText());
            FeatureDetailsModel.AddNewDetailsFeature(Integer.toString(index), ids);
            Stage current_stage = (Stage) name_detail.getScene().getWindow();
            Parent home_page = FXMLLoader.load(getClass().getResource("details-view.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }

    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) name_detail.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("knowledge-editor.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> result;
        try {
            result = DetailsModel.SelectAllDetails();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lv_details.getItems().addAll(result);

        lv_details.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                Stage current_stage = (Stage) lv_details.getScene().getWindow();
                Parent home_page = null;
                try {
                    home_page = FXMLLoader.load(getClass().getResource("info-about-detail.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene home_scene = new Scene(home_page);
                current_stage.setUserData(lv_details.getSelectionModel().getSelectedItem());
                current_stage.setScene(home_scene);
                current_stage.show();
            }
        });
    }
}
