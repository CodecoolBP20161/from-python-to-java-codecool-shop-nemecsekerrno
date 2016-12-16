package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;

import java.sql.*;

/**
 * Created by shevah on 13/12/16.
 */
public class CustomerDaoJdbc implements CustomerDao {

    public CustomerDaoJdbc() {

    }

    @Override
    public void add(Customer customer) throws SQLException {
        String query = "INSERT INTO customer (cust_first_name, cust_last_name, cust_email, cust_pw)" +
                " VALUES (?, ?, ?, ?);";
        try (Connection connection = DBController.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setString(1, customer.getFirstName());
            prepStatement.setString(2, customer.getLastName());
            prepStatement.setString(3, customer.getEmail());
            prepStatement.setString(4, customer.getPassword());
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isNewEmail(String email) throws SQLException {
        String query = "SELECT * FROM customer WHERE cust_email = ?;";
        try (Connection connection = DBController.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(query)){
            prepStatement.setString(1, email);
            ResultSet result= prepStatement.executeQuery();
            if (!result.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}