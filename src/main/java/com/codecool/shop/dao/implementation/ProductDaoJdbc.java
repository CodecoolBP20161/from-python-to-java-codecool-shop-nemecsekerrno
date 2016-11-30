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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shevah on 29/11/16.
 */
public class ProductDaoJdbc implements ProductDao {

    private static ProductDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    public ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
    String query = "INSERT INTO product (p_name, p_defaultprice, p_defaultcurrency, p_description," +
            " p_supplier, p_productcategory) VALUES ('"+ product.getName() + "', '" + product.getDefaultPrice() + "', '" +
            product.getDefaultCurrency() + "', '" + product.getDescription() + "', '" + product.getSupplier().getId() +
            "', '" + product.getProductCategory().getId() + "');";
        DBController.execUpdate(query);
    }

    @Override
    public Product find(int id) throws SQLException {
        String query = "SELECT * FROM product LEFT JOIN category ON p_category=category.c_id LEFT JOIN supplier ON product.p_supplier=supplier.s_id WHERE p_id ='" + id + "';";
        ResultSet result = DBController.execQuery(query);
        ProductCategory category = new ProductCategory(result.getString("c_name"), result.getString("c_department"), result.getString("c_description"));
        category.setId(result.getInt("p_productcategory"));
        Supplier supplier = new Supplier(result.getString("s_name"), result.getString("s_description"));
        supplier.setId(result.getInt("p_supplier"));
        Product product = new Product(result.getString("p_name"), result.getFloat("p_defaultprice"),
                result.getString("p_defaultcurrency"), result.getString("p_description"),
                category, supplier);
        product.setId(id);
        return product;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product WHERE p_id = '" + id +"';";
        DBController.execUpdate(query);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> allProducts = new ArrayList<>();
        String query = "SELECT p_id FROM product;";
        ResultSet result = DBController.execQuery(query);
        while (result.next()) {
            Product product = find(result.getInt("p_id"));
            allProducts.add(product);
        }
        return allProducts;
    }

    @Override
    public ArrayList<Product> getBy(Supplier supplier) throws SQLException {
        ArrayList<Product> allProducts = new ArrayList<>();
        String query = "SELECT p_id FROM product WHERE p_supplier=" + supplier.getId() + ";";
        ResultSet result = DBController.execQuery(query);
        while (result.next()) {
            Product product = find(result.getInt("p_id"));
            allProducts.add(product);
        }
        return allProducts;
    }

    @Override
    public ArrayList<Product> getBy(ProductCategory productCategory) throws SQLException {
        ArrayList allProducts = new ArrayList<>();
        String query = "SELECT p_id FROM product WHERE p_productcategory=" + productCategory.getId() + ";";
        ResultSet result = DBController.execQuery(query);
        while (result.next()) {
            Product product = find(result.getInt("p_id"));
            allProducts.add(product);
        }
        return allProducts;
    }

    @Override
    public void clearAll() {}
}
