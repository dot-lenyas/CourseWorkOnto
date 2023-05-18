package com.example.courseworkonto.model;

import com.example.courseworkonto.db_handler;

import java.sql.*;
import java.util.ArrayList;

public class RepairModel {

    public static int AddRepair(Integer id, String actions) throws SQLException {
        String sql = "INSERT INTO repairs (id_faults, actions) VALUES ('"+id+"', '"+actions+"')";
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

    public static ArrayList<String> SelectRepairByFaultId(Integer id) throws SQLException
    {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM repairs where id_faults='"+id+"'");
        while (rs.next())
        {
            str.add(rs.getString("id"));
        }
        return str;
    }

    public static ArrayList<String> SelectActionsById(Integer id) throws SQLException
    {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM repairs where id='"+id+"'");
        while (rs.next())
        {
            str.add(rs.getString("actions"));
        }
        return str;
    }

}
