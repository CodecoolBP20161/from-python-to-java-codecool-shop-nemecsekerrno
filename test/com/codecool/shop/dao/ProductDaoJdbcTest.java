package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDaoJdbcTest {
    ProductDaoJdbc productDaoJdbc;
    ProductCategoryDaoJdbc productCategoryDaoJdbc;
    SupplierDaoJdbc supplierDaoJdbc;
    
    Supplier amazon, lenovo;
    Product one, two, three;
    ProductCategory tablet, notebook;


    @Before
    public void setUp() throws SQLException {
        productCategoryDaoJdbc = new ProductCategoryDaoJdbc();
        supplierDaoJdbc = new SupplierDaoJdbc();
        productDaoJdbc = new ProductDaoJdbc();

        amazon = new Supplier("Amazon", "Digital content and services");
        amazon.setId(1);
        supplierDaoJdbc.add(amazon);
        lenovo = new Supplier("Lenovo", "Computers");
        lenovo.setId(2);
        supplierDaoJdbc.add(lenovo);

        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        tablet.setId(1);
        productCategoryDaoJdbc.add(tablet);
        notebook = new ProductCategory("Notebook", "Hardware", "A notebook for people that is very nice and useful.");
        notebook.setId(2);
        productCategoryDaoJdbc.add(notebook);

        one = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDaoJdbc.add(one);
        one.setId(1);
        two = new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        productDaoJdbc.add(two);
        two.setId(2);
        three = new Product("Lenovo 310-15IKB 15.6 Laptop", 390, "USD", "Intel Core i5 - 8GB Memory - 1TB Hard Drive", notebook, lenovo);
        productDaoJdbc.add(three);
        three.setId(3);
    }

    @After
    public void tearDown() {
        supplierDaoJdbc.clearAll();
        productCategoryDaoJdbc.clearAll();
        productDaoJdbc.clearAll();
    }

    @Test
    public void TestFindValidID() throws Exception {
        assertEquals("Amazon Fire", productDaoJdbc.find(1).getName());
    }

    @Test
    public void TestFindInvalidID() throws Exception {
        productDaoJdbc.remove(1);
        assertEquals(null, productDaoJdbc.find(1));
    }

    @Test
    public void TestGetAll() throws Exception {
        List<Product> list = new ArrayList();
        one.setId(1);
        list.add(one);
        two.setId(2);
        list.add(two);
        three.setId(3);
        list.add(three);
        assertEquals(list, productDaoJdbc.getAll());
    }

    @Test
    public void TestGetAllEmpty() throws Exception {
        productDaoJdbc.remove(1);
        productDaoJdbc.remove(2);
        productDaoJdbc.remove(3);
        List list = new ArrayList();
        assertEquals(list, productDaoJdbc.getAll());
    }

    @Test
    public void TestGetAllAfterRemoveInvalidID() throws Exception {
        productDaoJdbc.remove(-1);
        List list = new ArrayList();
        one.setId(1);
        list.add(one);
        two.setId(2);
        list.add(two);
        three.setId(3);
        list.add(three);
        assertEquals(list, productDaoJdbc.getAll());
    }

    @Test
    public void TestGetBySupplier() throws Exception {
        List list = new ArrayList();
        one.setId(1);
        list.add(one);
        assertEquals(list, productDaoJdbc.getBy(amazon));
    }

    @Test
    public void TestGetBySupplierWithNoproductDaoJdbc() throws Exception {
        productDaoJdbc.remove(1);
        List list = new ArrayList();
        assertEquals(list, productDaoJdbc.getBy(amazon));
    }

    @Test
    public void TestGetByProductCategory() throws Exception {
        List list = new ArrayList();
        three.setId(3);
        list.add(three);
        assertEquals(list, productDaoJdbc.getBy(notebook));
    }

    @Test
    public void TestGetByProductCategoryWithNoproductDaoJdbc() throws Exception {
        productDaoJdbc.remove(3);
        List list = new ArrayList();
        assertEquals(list, productDaoJdbc.getBy(notebook));
    }

}