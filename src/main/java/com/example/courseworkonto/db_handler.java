package com.example.courseworkonto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_handler {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/course_work","postgres", "4625");
    }
}
