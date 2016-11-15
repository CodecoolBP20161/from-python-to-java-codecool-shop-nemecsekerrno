package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        return categoryFilterHandler(0);
    }

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        int categoryId = Integer.parseInt(req.params(":categoryId"));
        return categoryFilterHandler(categoryId);
    }

    private static ModelAndView categoryFilterHandler(int categoryId) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        if (categoryId == 0) {
            params.put("products", productDataStore.getAll());
        } else {
            params.put("currCategory", productCategoryDataStore.find(categoryId));
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(categoryId)));
        }
        return new ModelAndView(params, "product/index");
    }
}
