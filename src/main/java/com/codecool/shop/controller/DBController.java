package com.codecool.shop.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void executeQuery(String query) {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement())
        {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void executeQuery(String query, Object... args){
//        try (Connection connection = getConnection();
//             PreparedStatement prStatement = connection.prepareStatement(query))
//        {
//            for (int i = 0; i < args.length; i++) {
//                String objType = args[i].getClass().getName();
//                if (Objects.equals(objType, new String(""))) {
//
//                }
//
//            }
//            prStatement.setString(1, todo.title);
//            prStatement.setString(2, todo.id);
//            prStatement.setObject(3, todo.status, Types.VARCHAR);
//            prStatement.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
