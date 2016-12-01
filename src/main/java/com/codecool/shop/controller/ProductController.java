package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.*;

import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static Map params = new HashMap<>();
    private static ProductCategoryDaoJdbc productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
    private static SupplierDaoJdbc supplierDaoJdbc = new SupplierDaoJdbc();
    private static ProductDaoJdbc productDaoJdbc = new ProductDaoJdbc();

    public static ModelAndView renderAllProducts(Request req, Response res) throws SQLException {
        generalHandler(req);
        UrlController.saveLastURL(req);
        return new ModelAndView(params, "product/viewproducts");
    }

    public static ModelAndView renderProductsByCategory(Request req, Response res) throws SQLException {
        ProductCategory productCategory = productCategoryDaoJdbc.find(Integer.parseInt(req.params(":id")));
        filterHandler(req, productCategory);
        UrlController.saveLastURL(req);
        return new ModelAndView(params, "product/viewproducts");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res) throws SQLException {
        Supplier supplier = supplierDaoJdbc.find(Integer.parseInt(req.params(":id")));
        filterHandler(req, supplier);
        UrlController.saveLastURL(req);
        return new ModelAndView(params, "product/viewproducts");
    }

    // handler for shopping cart
    public static ModelAndView handleAddToCart(Request req, Response res) throws SQLException {
        Product prod = productDaoJdbc.find(Integer.parseInt(req.params(":prodID")));
        Integer qty = Integer.parseInt(req.queryParams("prodQty"));
        ShoppingCart cart = new ShoppingCart();
        if (req.session().attribute("cart") != null) {
            cart = req.session().attribute("cart");
        }
        cart.addToCart(prod, qty);
        req.session().attribute("cart", cart);
        res.redirect(UrlController.getLastURL(req));
        return null;
    }

    // handler for general content
    private static void generalHandler(Request req) throws SQLException {
        ShoppingCart cart = req.session().attribute("cart");

        params.put("categories", productCategoryDaoJdbc.getAll());
        params.put("suppliers", supplierDaoJdbc.getAll());
        params.put("currFilter", null);
        params.put("products", productDaoJdbc.getAll());
        params.put("cartItemTotal", cart != null ? cart.totalQuantity() : null);
    }

    // handler for filtering by category
    private static void filterHandler(Request req, ProductCategory filter) throws SQLException {
        generalHandler(req);
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }

    // handler for filtering by supplier
    private static void filterHandler(Request req, Supplier filter) throws SQLException {
        generalHandler(req);
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }
}