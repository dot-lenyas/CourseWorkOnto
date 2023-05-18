package com.example.courseworkonto.model;


import com.example.courseworkonto.db_handler;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddFeatureModel {

    public static void AddNewFeature(String name, String type, String values, String normal_values) throws SQLException {
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        st.execute("INSERT INTO public.features (имя, тип, допустимые_значения, нормальные_значения) VALUES ('"+name+"', '"+type+"', '"+values+"', '"+normal_values+"');");
    }

    public static ArrayList<String> SelectFeatures() throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM features");
        while (rs.next())
        {
            str.add(rs.getString("имя"));
        }
        return str;
    }

    public static ArrayList<String> SelectFeaturesByName(String name) throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM features where имя='"+name+"'");
        while (rs.next())
        {
            str.add(rs.getString("имя"));
            str.add(rs.getString("тип"));
            str.add(rs.getString("допустимые_значения"));
            str.add(rs.getString("id"));
        }
        return str;
    }

}
