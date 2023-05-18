package com.example.courseworkonto;

import com.example.courseworkonto.model.AddFeatureModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FeaturesViewController implements Initializable {

    @FXML
    private ListView<String> lv_features;

    @FXML
    protected void featureAddRedirect(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        Stage current_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("add-feature-view.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> names = new ArrayList<String>();
        try {
            names = AddFeatureModel.SelectFeatures();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lv_features.getItems().addAll(names);
        lv_features.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    showInfo(lv_features.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) lv_features.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("knowledge-editor.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }

    private void showInfo(String item) throws IOException {
        Stage current_stage = (Stage) lv_features.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("info-about-feature.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setUserData(item);
        current_stage.setScene(home_scene);
        current_stage.show();
    }
}
