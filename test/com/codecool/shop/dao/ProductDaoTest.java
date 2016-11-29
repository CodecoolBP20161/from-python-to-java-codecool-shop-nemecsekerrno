package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoTest {
    ProductDao products;
    Supplier amazon, lenovo;
    Product one, two, three;
    ProductCategory tablet, notebook;


    @Before
    public void setUp() {
        products = ProductDaoMem.getInstance();

        amazon = new Supplier("Amazon", "Digital content and services");
        lenovo = new Supplier("Lenovo", "Computers");

        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        notebook = new ProductCategory("Notebook", "Hardware", "A notebook for people that is very nice and useful.");

        one = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        two = new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        three = new Product("Lenovo 310-15IKB 15.6 Laptop", 390, "USD", "Intel Core i5 - 8GB Memory - 1TB Hard Drive", notebook, lenovo);
    }

    @Test
    public void TestFindValidID() throws Exception {
        products.add(one);
        assertEquals("Amazon Fire", products.find(1).getName());
    }

    @Test
    public void TestFindInvalidID() throws Exception {
        products.add(one);
        products.remove(1);
        assertEquals(null, products.find(1));
    }

    @Test
    public void TestGetAll() throws Exception {
        products.add(one);
        products.add(two);
        List list = new ArrayList();
        list.add(one);
        list.add(two);
        assertEquals(list, products.getAll());
    }

    @Test
    public void TestGetAllEmpty() throws Exception {
        List list = new ArrayList();
        assertEquals(list, products.getAll());
    }

    @Test
    public void TestGetAllAfterRemoveInvalidID() throws Exception {
        products.add(one);
        products.remove(-1);
        List list = new ArrayList();
        list.add(one);
        assertEquals(list, products.getAll());
    }

    @Test
    public void TestGetBySupplier() throws Exception {
        products.add(one);
        products.add(two);
        List list = new ArrayList();
        list.add(one);
        assertEquals(list, products.getBy(amazon));
    }

    @Test
    public void TestGetBySupplierWithNoProducts() throws Exception {
        products.add(one);
        List list = new ArrayList();
        assertEquals(list, products.getBy(lenovo));
    }

    @Test
    public void TestGetByProductCategory() throws Exception {
        products.add(one);
        products.add(three);
        List list = new ArrayList();
        list.add(three);
        assertEquals(list, products.getBy(notebook));
    }

    @Test
    public void TestGetByProductCategoryWithNoProducts() throws Exception {
        products.add(one);
        List list = new ArrayList();
        assertEquals(list, products.getBy(notebook));
    }

    @After
    public void tearDown() {
        products.clearAll();
    }
}