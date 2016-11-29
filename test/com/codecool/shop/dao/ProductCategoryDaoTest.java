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
    // virtual list of product categories to test against
    private List<ProductCategory> testProductCategories;

    @Before
    public void setUp() throws Exception {
        productCategoryDao = ProductCategoryDaoMem.getInstance();
        testProductCategories = new ArrayList();

        productCategory1 = new ProductCategory("name1", "department1", "description1");
        productCategoryDao.add(productCategory1);
        testProductCategories.add(productCategory1);

        productCategory2 = new ProductCategory("name2", "department2", "description2");
        productCategoryDao.add(productCategory2);
        testProductCategories.add(productCategory2);

    }

    @After
    public void tearDown() throws Exception {
        productCategoryDao.clear();
    }

    @Test
    public void TestFind() throws Exception {
        assertEquals(productCategoryDao.find(1), productCategory1);
    }

    @Test
    public void TestFindInvalidID() throws Exception {
        assertEquals(productCategoryDao.find(0), null);
    }

    @Test
    public void TestGetAll() throws Exception {
        assertEquals(productCategoryDao.getAll(), testProductCategories);
    }

    @Test
    public void TestRemove() throws Exception {
        productCategoryDao.remove(2);
        assertEquals(productCategoryDao.find(2), null);
    }

    @Test
    // getAll() should give the same result after removing a category with an invalid ID
    public void TestRemoveInvalidID() throws Exception {
        productCategoryDao.remove(0);
        assertEquals(productCategoryDao.getAll(), testProductCategories);
    }
}