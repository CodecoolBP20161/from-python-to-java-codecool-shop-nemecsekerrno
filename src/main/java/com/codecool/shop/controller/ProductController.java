package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.Supplier;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.StringJoiner;

public class ProductController {

    private static Map params = new HashMap<>();

    public static ModelAndView renderProducts(Request req, Response res) {
        filterHandler(req);
        saveLastURL(req);
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        filterHandler(req, ProductCategoryDaoMem.getInstance().find(Integer.parseInt(req.params(":id"))));
        saveLastURL(req);
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        filterHandler(req, SupplierDaoMem.getInstance().find(Integer.parseInt(req.params(":id"))));
        saveLastURL(req);
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView handleAddToCart(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Integer prodID = Integer.parseInt(req.params(":prodID"));
        LineItem lineItem = new LineItem(productDataStore.find(prodID));
        if (req.session().attribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            cart.getSessionItems().add(lineItem);
            req.session().attribute("cart", cart);
        } else {
            ShoppingCart cart = req.session().attribute("cart");
            cart.addToCart(lineItem);
            req.session().attribute("cart", cart);
        }
        res.redirect(getLastURL(req));
        return null;
    }

    private static void filterHandler(Request req) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ShoppingCart cart = req.session().attribute("cart");

        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("currFilter", null);
        params.put("products", productDataStore.getAll());
        params.put("cartItemTotal", cart != null ? cart.totalQuantity() : null);
    }

    private static void filterHandler(Request req, ProductCategory filter) {
        filterHandler(req);
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }

    private static void filterHandler(Request req, Supplier filter) {
        filterHandler(req);
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }

    private static void saveLastURL(Request req) {
        req.session().attribute("lastURL", req.pathInfo());
    }

    private static String getLastURL(Request req) {
        String redirectURL = "";
        try {
            redirectURL = req.session().attribute("lastURL");
        } catch (NullPointerException e) {
            redirectURL = "/";
        }
        return redirectURL;
    }
}
