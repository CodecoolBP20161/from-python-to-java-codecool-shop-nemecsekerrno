package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        String query = " INSERT INTO productcategory (c_name, c_department) " +
                "VALUES ('" + category.getName() + "', '" + category.getDepartment() + "');";
        try (Connection connection = DBController.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) throws SQLException {
        String query = "SELECT * FROM product LEFT JOIN category ON p_category=category.c_id LEFT JOIN supplier ON product.p_supplier=supplier.s_id WHERE p_id ='" + id + "';";
        try (Connection connection = DBController.getConnection(); Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery(query);
            ProductCategory category = new ProductCategory(result.getString("c_name"), result.getString("c_department"), result.getString("c_desciption"));
            category.setId(result.getInt("c_id"));
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM productcategory WHERE c_id='" + id + "';";
        try (Connection connection = DBController.getConnection(); Statement statement = connection.createStatement())
        {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> allCategories = new ArrayList<>();
        String query = "SELECT * FROM productcategory";
        try (Connection connection = DBController.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ProductCategory current = new ProductCategory(result.getString("c_name"), result.getString("c_department"), result.getString("c_description"));
                allCategories.add(current);
            }
            return allCategories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clearAll() {}
}
