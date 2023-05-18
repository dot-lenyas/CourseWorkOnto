package com.example.courseworkonto;

import com.example.courseworkonto.model.AddFeatureModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddFeatureViewController implements Initializable {

    @FXML
    private TextField ch_name;

    @FXML
    private ChoiceBox<String> cb_type;

    @FXML
    private TextArea text_values;

    @FXML
    private TextArea normal_values;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] str = {"Количественный", "Качественный"};
        cb_type.getItems().addAll(str);
        cb_type.setValue(str[0]);
    }

    @FXML
    protected void featureAddRedirect(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        AddFeatureModel.AddNewFeature(ch_name.getText(), cb_type.getValue(), text_values.getText(), normal_values.getText());
        Stage current_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent home_page = FXMLLoader.load(getClass().getResource("features-view.fxml"));
        Scene home_scene = new Scene(home_page);
        current_stage.setScene(home_scene);
        current_stage.show();
    }
}
