package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.List;

/**
 * Created by hamargyuri on 2016. 11. 29..
 */
public class SupplierDaoJdbc implements SupplierDao {
    private static SupplierDaoJdbc instance = null;
    private String query;

    private SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier category) {
        query = "INSERT INTO suppliers VALUES (" + category.getName() + ", " + category.getDescription() + ");";
        DBController.executeQuery(query);
    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
        query = "DELETE FROM suppliers WHERE id=" + id + ";";
        DBController.executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
