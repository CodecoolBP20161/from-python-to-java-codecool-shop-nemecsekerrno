package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamargyuri on 2016. 11. 29..
 */
public class SupplierDaoJdbc implements SupplierDao {
    private static SupplierDaoJdbc instance = null;
    private String query;
    private ResultSet res;

    public SupplierDaoJdbc() {
    }

    public static SupplierDaoJdbc getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        query = "INSERT INTO supplier (s_name, s_description) VALUES ('" + supplier.getName() + "', '" + supplier.getDescription() + "');";
        DBController.execUpdate(query);
    }

    @Override
    public Supplier find(int id) throws SQLException {
        query = "SELECT * FROM supplier WHERE s_id='" + id + "';";
        res = DBController.execQuery(query);
        res.first();
        Supplier supp = new Supplier(res.getString("s_name"), res.getString("s_description"));
        supp.setId(id);
        return supp;
    }

    @Override
    public void remove(int id) {
        query = "DELETE FROM supplier WHERE s_id='" + id + "';";
        DBController.execUpdate(query);
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> suppliers = new ArrayList();
        query = "SELECT * FROM supplier;";
        res = DBController.execQuery(query);
        while (res.next()) {
            Supplier supp = new Supplier(res.getString("s_name"), res.getString("s_description"));
            supp.setId(res.getInt("s_id"));
            supp.setProducts(ProductDaoJdbc.getInstance().getBy(supp));
            suppliers.add(supp);
        }
        return suppliers;
    }

    @Override
    public void clearAll() {}
}
