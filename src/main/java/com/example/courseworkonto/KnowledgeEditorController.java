package com.example.courseworkonto;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KnowledgeEditorController implements Initializable {

    @FXML
    private ListView<String> lv_details;

    String[] editors = {"Детали", "Характеристики", "Диагнозы", "Действия", "Ремонты"};

    @FXML
    protected void detailsShow(ActionEvent event) throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv_details.getItems().addAll(editors);

        lv_details.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    changeScene(lv_details.getSelectionModel().getSelectedItem());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void changeScene(String name) throws IOException {
        Stage current_stage = (Stage) lv_details.getScene().getWindow();
        if (name.equals("Детали"))
        {
            Parent home_page = FXMLLoader.load(getClass().getResource("details-view.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }
        else if (name.equals("Характеристики"))
        {
            Parent home_page = FXMLLoader.load(getClass().getResource("features-view.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }
        else if (name.equals("Диагнозы"))
        {
            Parent home_page = FXMLLoader.load(getClass().getResource("fault_view.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }
        else if (name.equals("Действия"))
        {
            Parent home_page = FXMLLoader.load(getClass().getResource("actions.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }
        else if (name.equals("Ремонты"))
        {
            Parent home_page = FXMLLoader.load(getClass().getResource("repair.fxml"));
            Scene home_scene = new Scene(home_page);
            current_stage.setScene(home_scene);
            current_stage.show();
        }


    }
}
