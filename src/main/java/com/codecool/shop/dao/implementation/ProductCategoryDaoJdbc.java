package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = " INSERT INTO productcategory (name, department) " +
                "VALUES ('" + category.getName() + "', '" + category.getDepartment() + "');";
        DBController.executeQuery(query);
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        String query = "SELECT * FROM productcategory WHERE id ='" + id + "';";
        ResultSet result = DBController.executeQuery(query);
        ProductCategory shit = new ProductCategory(result.getString("name"), result.getString("department"),
                result.getString("description"));
        return shit;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategory WHERE id='" + id + "';";
        DBController.executeQuery(query);
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * from productcategory";
        return null;
    }
}
