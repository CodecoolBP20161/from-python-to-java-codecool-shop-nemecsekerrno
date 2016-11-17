package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static Map params = new HashMap<>();

    public static ModelAndView renderAllProducts(Request req, Response res) {
        generalHandler(req);
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

    // handler for shopping cart
    public static ModelAndView handleAddToCart(Request req, Response res) {
        Product prod = ProductDaoMem.getInstance().find(Integer.parseInt(req.params(":prodID")));
        Integer qty = Integer.parseInt(req.queryParams("prodQty"));
        ShoppingCart cart = new ShoppingCart();
        if (req.session().attribute("cart") != null) {
            cart = req.session().attribute("cart");}
        cart.addToCart(prod, qty);
        req.session().attribute("cart", cart);
        res.redirect(getLastURL(req));
        return null;
    }

    // handler for general content
    private static void generalHandler(Request req) {
        ShoppingCart cart = req.session().attribute("cart");

        params.put("categories", ProductCategoryDaoMem.getInstance().getAll());
        params.put("suppliers", SupplierDaoMem.getInstance().getAll());
        params.put("currFilter", null);
        params.put("products", ProductDaoMem.getInstance().getAll());
        params.put("cartItemTotal", cart != null ? cart.totalQuantity() : null);
    }

    // handler for filtering by category
    private static void filterHandler(Request req, ProductCategory filter) {
        generalHandler(req);
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }

    // handler for filtering by supplier
    private static void filterHandler(Request req, Supplier filter) {
        generalHandler(req);
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }

    // after an item has been added to the cart, page automatically redirects to previous url
    private static void saveLastURL(Request req) {
        req.session().attribute("lastURL", req.pathInfo());
    }

    private static String getLastURL(Request req) {
        String redirectURL;
        try {
            redirectURL = req.session().attribute("lastURL");
        } catch (NullPointerException e) {
            redirectURL = "/";
        }
        return redirectURL;
    }
}
