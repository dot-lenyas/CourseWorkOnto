package com.example.courseworkonto;

import com.example.courseworkonto.model.AddFeatureModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InfoAboutFeatureController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private Label type;

    @FXML
    private Label values;

    @FXML
    private Button back_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    protected void RefreshPage() throws SQLException {
        Stage current_stage = (Stage) name.getScene().getWindow();
        Object feature_object = current_stage.getUserData();
        String feature_name = (String) feature_object;
        ArrayList<String> result = AddFeatureModel.SelectFeaturesByName(feature_name);
        name.setText("Имя: " + result.get(0));
        type.setText("Тип: " + result.get(1));
        String[] res = result.get(2).split("\n");
        String str = "";
        for (int i = 0; i < res.length; i++)
        {
            str += res[i] + ", ";
        }
        values.setText("Допустимые значения: " + str);
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage current_stage = (Stage) back_button.getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("features-view.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }
}
