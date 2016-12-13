package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.CustomerDao;
import com.codecool.shop.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by shevah on 13/12/16.
 */
public class CustomerDaoJdbc implements CustomerDao {

    public CustomerDaoJdbc() {

    }

    @Override
    public void add(Customer customer) throws SQLException {
        if (isNewUser(customer.getEmail())) {
            String query = "INSERT INTO customer (c_first_name, c_last_name, c_email, c_pw)" +
                    " VALUES ('"+ customer.getFirstName() + "', '" + customer.getLastName() + "', '"
                    + customer.getEmail() + "', '" + customer.getPassword() + "');";
            try (Connection connection = DBController.getConnection(); Statement statement = connection.createStatement()){
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            //here comes the route call that tells the user, that the email is already in the database
        }
    }
    public boolean isNewUser(String email) throws SQLException {
        String query = "SELECT * FROM customer WHERE c_email ='" + email + "';";
        try (Connection connection = DBController.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            if (!result.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        }
    }

