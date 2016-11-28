package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hamargyuri on 2016. 11. 28..
 */
public class ProductCategoryDaoTest {
    private ProductCategory productCategory1;
    private ProductCategory productCategory2;
    private ProductCategoryDao productCategoryDao;

    @Before
    public void setUp() throws Exception {
        productCategory1 = new ProductCategory("name1", "department1", "description1");
        productCategory2 = new ProductCategory("name2", "department2", "description2");
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        productCategoryDao.add(productCategory1);
        productCategoryDao.add(productCategory2);
    }

    @After
    public void tearDown() throws Exception {
        productCategoryDao.clear();
    }

    @Test
    public void find() throws Exception {
        assertEquals(productCategoryDao.find(1), productCategory1);
    }

    @Test
    public void getAll() throws Exception {
        List<ProductCategory> productCategories = new ArrayList();
        productCategories.add(productCategory1);
        productCategoryDao.remove(2);
        assertEquals(productCategoryDao.getAll(), productCategories);
    }

}