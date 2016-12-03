package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {

    void add(Supplier category);
    Supplier find(int id) throws SQLException;
    void remove(int id);

    List<Supplier> getAll() throws SQLException;
    void clearAll();
}
