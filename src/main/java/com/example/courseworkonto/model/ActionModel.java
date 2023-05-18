package com.example.courseworkonto.model;

import com.example.courseworkonto.db_handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActionModel {

    public static void AddNewAction(String name) throws SQLException {
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        st.execute("INSERT INTO public.action (имя) VALUES ('"+name+"')");
    }

    public static ArrayList<String> SelectActions() throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM action");
        while (rs.next())
        {
            str.add(rs.getString("имя"));
        }
        return str;
    }

    public static ArrayList<String> SelectActionByName(String name) throws SQLException {
        ArrayList<String> str = new ArrayList<String>();
        Connection con = db_handler.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM action WHERE имя='"+name+"'");
        while (rs.next())
        {
            str.add(rs.getString("id"));
        }
        return str;
    }


}
