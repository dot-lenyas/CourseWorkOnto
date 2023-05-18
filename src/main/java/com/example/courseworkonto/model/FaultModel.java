package com.example.courseworkonto.model;

import com.example.courseworkonto.db_handler;

import java.sql.*;
import java.util.ArrayList;

public class FaultModel {
    public static int AddFault(String name) throws SQLException {
        String sql = "INSERT INTO faults (имя) VALUES ('"+name+"')";
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

    public static ArrayList<String> SelectAllFaults() throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM faults");
        while (rs.next())
        {
            str.add(rs.getString("имя"));
        }
        return str;
    }

    public static ArrayList<String> SelectFaultByName(String name) throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM faults where имя='"+name+"'");
        while (rs.next())
        {
            str.add(rs.getString("id"));
            str.add(rs.getString("имя"));
        }
        return str;
    }
}
