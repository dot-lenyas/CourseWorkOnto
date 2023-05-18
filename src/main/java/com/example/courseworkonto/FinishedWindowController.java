package com.example.courseworkonto;

import com.example.courseworkonto.model.RepairModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class FinishedWindowController {


    @FXML
    private ListView<String> lv_actions;

    @FXML
    protected void GetList() throws SQLException {
        Stage current_stage = (Stage) lv_actions.getScene().getWindow();
        Object obj = current_stage.getUserData();
        String result = (String) obj;
        ArrayList<String> res = RepairModel.SelectActionsById(Integer.parseInt(result));
        String str = res.get(0);
        lv_actions.getItems().addAll(str.split("\n"));
    }


}
