package com.codecool.shop.dao;

import com.codecool.shop.model.Customer;

import java.sql.SQLException;

/**
 * Created by berloc on 2016.12.13..
 */
public interface CustomerDao {

    void add(Customer customer) throws SQLException;
    boolean isNewEmail(String email) throws SQLException;
}
