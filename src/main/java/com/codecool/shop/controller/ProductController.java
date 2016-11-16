package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static Map params = new HashMap<>();

    public static ModelAndView renderProducts(Request req, Response res) {
        filterHandler();
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByCategory(Request req, Response res) {
        filterHandler(ProductCategoryDaoMem.getInstance().find(Integer.parseInt(req.params(":id"))));
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsBySupplier(Request req, Response res) {
        filterHandler(SupplierDaoMem.getInstance().find(Integer.parseInt(req.params(":id"))));
        return new ModelAndView(params, "product/index");
    }

    private static void filterHandler() {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("currFilter", null);
        params.put("products", productDataStore.getAll());
    }

    private static void filterHandler(ProductCategory filter) {
        filterHandler();
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }

    private static void filterHandler(Supplier filter) {
        filterHandler();
        params.put("currFilter", filter);
        params.put("products", filter.getProducts());
    }
}
