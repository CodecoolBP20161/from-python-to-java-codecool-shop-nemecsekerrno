package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private static ProductCategoryDaoJdbc instance = null;


    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        String query = " INSERT INTO productcategory (c_name, c_department) " +
                "VALUES ('" + category.getName() + "', '" + category.getDepartment() + "');";
        DBController.execUpdate(query);
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        String query = "SELECT * FROM productcategory WHERE c_id ='" + id + "';";
        ResultSet result = DBController.execQuery(query);
        ProductCategory category = new ProductCategory(result.getString("c_name"), result.getString("c_department"),
                result.getString("c_description"));
        return category;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategory WHERE c_id='" + id + "';";
        DBController.execUpdate(query);
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> allCategories = new ArrayList<>();
        String query = "SELECT * FROM productcategory";
        ResultSet result = DBController.execQuery(query);
        while (result.next()) {
            ProductCategory current = new ProductCategory(result.getString("c_name"), result.getString("c_department"), result.getString("c_description"));
            allCategories.add(current);
        }
        return allCategories;
    }

    @Override
    public void clearAll() {}
}
