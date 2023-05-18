package com.example.courseworkonto.model;

import com.example.courseworkonto.db_handler;

import java.sql.*;
import java.util.ArrayList;

public class DetailsModel {
    public static ArrayList<String> SelectAllDetails() throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM details");
        while (rs.next())
        {
            str.add(rs.getString("имя"));
        }
        return str;
    }

    public static int AddDetail(String name) throws SQLException {
        String sql = "INSERT INTO details (имя) VALUES ('"+name+"')";
        Connection con = db_handler.getConnection();
        PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.execute();
        ResultSet rs = st.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next())
        {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;

    }

    public static ArrayList<String> SelectFeatureByName(String name) throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM features where имя='"+name+"'");
        while (rs.next())
        {
            str.add(rs.getString("id"));
        }
        return str;
    }
    public static ArrayList<String> SelectDetailByName(String name) throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM details where имя='"+name+"'");
        while (rs.next())
        {
            str.add(rs.getString("id"));
            str.add(rs.getString("имя"));
        }
        return str;
    }

}
