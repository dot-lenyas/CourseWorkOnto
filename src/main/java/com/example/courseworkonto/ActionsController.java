package com.example.courseworkonto;

import com.example.courseworkonto.model.ActionModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ActionsController implements Initializable {
    
    
    @FXML
    private TextField action_name;

    @FXML
    private ListView<String> lv_actions;
    
    @FXML
    protected void AddAction() throws SQLException, IOException {
        ActionModel.AddNewAction(action_name.getText());
        Stage current_stage = (Stage) action_name.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("actions.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) action_name.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("knowledge-editor.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> result;
        try {
            result = ActionModel.SelectActions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lv_actions.getItems().addAll(result);
    }
}
