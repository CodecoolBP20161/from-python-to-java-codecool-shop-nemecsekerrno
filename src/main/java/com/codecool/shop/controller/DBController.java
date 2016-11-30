package com.codecool.shop.controller;


import java.sql.*;
import java.util.Objects;

public abstract class DBController {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public static void execUpdate(String query) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet execQuery(String query) {
        ResultSet result = null;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
