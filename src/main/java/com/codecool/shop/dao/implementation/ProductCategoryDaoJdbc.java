package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * Created by berloc on 2016.11.29..
 */
public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        String query = " INSERT INTO productcategory (name, department) " +
                "VALUES ('" + category.getName() + "', '" + category.getDepartment() + "');";
        DBController.executeQuery(query);
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM productcategory WHERE id ='" + id + "';";
        DBController.executeQuery(query);
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategory WHERE id='" + id + "';";
        DBController.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * from productcategory";
        DBController.executeQuery(query);
        return null;
    }
}
