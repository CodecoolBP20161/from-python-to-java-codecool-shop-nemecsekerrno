package com.codecool.shop.dao;

import com.codecool.shop.model.Customer;

/**
 * Created by berloc on 2016.12.13..
 */
public interface CustomerDao {

    void add(Customer customer);
    Customer find(String email);
}
