package com.codecool.shop.controller;

import java.sql.*;

public abstract class DBController {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "berloc";
    private static final String DB_PASSWORD = "codecool2016";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }
}
