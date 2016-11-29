package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.DBController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shevah on 29/11/16.
 */
public class ProductDaoJdbc implements ProductDao {

    private static ProductDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
    String query = "INSERT INTO product (name, defaultprice, defaultcurrency, description," +
            " supplier, productcategory) VALUES ('"+ product.getName() + "', '" + product.getDefaultPrice() + "', '" +
            product.getDefaultCurrency() + "', '" + product.getDescription() + "', '" + product.getSupplier().getId() +
            "', '" + product.getProductCategory().getId() + "');";
        DBController.executeQuery(query);
    }

    @Override
    public Product find(int id) throws SQLException {
        ProductCategoryDao category = ProductCategoryDaoJdbc.getInstance();
        String query = "SELECT * FROM product WHERE id ='" + id + "';";
        ResultSet result = DBController.executeQuery(query);
        ProductCategory category1 = category.find(Integer.parseInt(result.getString("categoryId")));
        SupplierDao supplier = SupplierDaoJdbc.getInstance();
        Supplier supplier1 = supplier.find(Integer.parseInt(result.getString("supplierId")));
        Product resultProduct = new Product(result.getString("name"), result.getFloat("defaultprice"),
                result.getString("defaultcurrency"), result.getString("description"),
                category1, supplier1);
        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product WHERE id = '" + id +"';";
        DBController.executeQuery(query);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM product;";
        // something else comes here so we can return the Products
        ResultSet result = DBController.executeQuery(query);
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "DELETE FROM product WHERE supplier = '" + supplier.getId() +"';";
        // something else comes here so we can return the Products
        ResultSet result = DBController.executeQuery(query);
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "DELETE FROM product WHERE productcategory = '" + productCategory.getId() +"';";
        // something else comes here so we can return the Products
        ResultSet result = DBController.executeQuery(query);
        return null;
    }

    @Override
    public void clearAll() {}
}
