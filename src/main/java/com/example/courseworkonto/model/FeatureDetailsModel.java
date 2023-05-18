package com.example.courseworkonto.model;

import com.example.courseworkonto.db_handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FeatureDetailsModel {

    public static void AddNewDetailsFeature(String detail_id, ArrayList<String> features_id) throws SQLException {
        Connection con = db_handler.getConnection();
        String feature_id = "";
        String sql = "";
        Statement st = con.createStatement();
        for (int i = 0; i < features_id.size(); i++)
        {
            feature_id = features_id.get(i);
            sql = "INSERT INTO features_details (id_details, id_features) VALUES ('"+Integer.parseInt(detail_id)+"', '"+Integer.parseInt(feature_id)+"')";
            st.execute(sql);
        }
    }

    public static ArrayList<String> SelectFeaturesName(String detail_id) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT features.имя FROM features JOIN features_details fd on features.id = fd.id_features WHERE id_details = '"+Integer.parseInt(detail_id)+"'");
        while (rs.next())
        {
            result.add(rs.getString("имя"));
        }
        return result;
    }

    public static ArrayList<String> SelectFeaturesAndValuesName(String fault_id) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT features.имя, values as значения FROM features JOIN faults_features ff on features.id = ff.id_features WHERE id_faults = '"+Integer.parseInt(fault_id)+"'");
        while (rs.next())
        {
            result.add(rs.getString("имя") + "=" + rs.getString("значения"));
        }
        return result;
    }
}
