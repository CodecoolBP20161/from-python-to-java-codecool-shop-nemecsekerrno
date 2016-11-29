package com.codecool.shop.controller;


import java.sql.*;
import java.util.Objects;

public abstract class DBController {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public static ResultSet executeQuery(String query) {
        ResultSet result = null;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void executeQueryWithPreparedStatement(String query, Object... args){
        try (Connection connection = getConnection();
             PreparedStatement prStatement = connection.prepareStatement(query))
        {
            for (int i = 0; i < args.length; i++) {
                String objType = args[i].getClass().getName();
                if (Objects.equals(objType, new String("String"))) {
                    prStatement.setString(i++, args[i].toString());
                } else if (Objects.equals(objType, new String("Integer"))) {
                    prStatement.setInt(i++, (Integer) args[i]);
                }
            }
            prStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
