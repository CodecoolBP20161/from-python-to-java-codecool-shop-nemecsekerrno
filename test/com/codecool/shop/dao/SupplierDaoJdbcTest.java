package com.codecool.shop.dao;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shevah on 01/12/16.
 */
public class SupplierDaoJdbcTest {

    Supplier something;
    Supplier something2;
    SupplierDao supplierTester;

    @Before
    public void setUp() throws Exception {

        something = new Supplier("test", "test");
        something.setId(1);
        something2 = new Supplier("test2", "test2");
        something2.setId(2);
        supplierTester = new SupplierDaoJdbc();
    }
    @After
    public void tearDown() {
        supplierTester.clearAll();
    }

    @Test
    public void TestFindValidIndex() throws Exception {
        supplierTester.add(something);
        supplierTester.add(something2);
        assertEquals("test2", supplierTester.find(2).getName());
    }

    @Test
    public void TestFindInvalidIndex() throws Exception {
        assertEquals(null, supplierTester.find(1));
    }

    @Test
    public void TestRemove() throws Exception {
        supplierTester.add(something);
        supplierTester.add(something2);
        supplierTester.remove(1);
        assertEquals(1, supplierTester.getAll().size());
    }

    @Test
    public void TestRemoveInvalidIndex() throws Exception {
        supplierTester.add(something);
        supplierTester.add(something2);
        supplierTester.remove(5);
        assertEquals(2, supplierTester.getAll().size());
    }

    @Test
    public void TestGetAll() throws Exception {
        List<Supplier> list = new ArrayList();
        list.add(something);
        list.add(something2);
        supplierTester.add(something);
        supplierTester.add(something2);
        assertEquals(list.get(1), supplierTester.getAll().get(1));
    }
    @Test
    public void TestGetAllEmpty() throws Exception {
        List list = new ArrayList();
        assertEquals(list, supplierTester.getAll());
    }

}