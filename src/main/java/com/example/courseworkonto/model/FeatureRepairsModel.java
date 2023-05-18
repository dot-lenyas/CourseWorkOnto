package com.example.courseworkonto.model;

import com.example.courseworkonto.db_handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FeatureRepairsModel {

    public static void AddNewRepairFeature(String repair_id, ArrayList<String> features_id, ArrayList<String> value) throws SQLException {
        Connection con = db_handler.getConnection();
        String feature_id = "";
        String sql = "";
        Statement st = con.createStatement();
        for (int i = 0; i < features_id.size(); i++)
        {
            feature_id = features_id.get(i);
            sql = "INSERT INTO features_repairs (id_repairs, id_features, values) VALUES ('"+Integer.parseInt(repair_id)+"', '"+Integer.parseInt(feature_id)+"', '"+value.get(i)+"')";
            st.execute(sql);
        }
    }

}
