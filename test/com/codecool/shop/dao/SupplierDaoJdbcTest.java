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

    private Supplier something;
    private Supplier something2;
    private SupplierDao supplierTester;

    @Before
    public void setUp() throws Exception {

        supplierTester = new SupplierDaoJdbc();
        something = new Supplier("test", "test");
        something2 = new Supplier("test2", "test2");
        supplierTester.add(something);
        supplierTester.add(something2);
    }
    @After
    public void tearDown() {
        supplierTester.clearAll();
    }

    @Test
    public void TestFindValidIndex() throws Exception {
        assertEquals("test2", supplierTester.find(2).getName());
    }

    @Test
    public void TestFindInvalidIndex() throws Exception {
        assertEquals(null, supplierTester.find(-2));
    }

    @Test
    public void TestRemove() throws Exception {
        supplierTester.remove(1);
        assertEquals(1, supplierTester.getAll().size());
    }

    @Test
    public void TestRemoveInvalidIndex() throws Exception {
        supplierTester.remove(5);
        assertEquals(2, supplierTester.getAll().size());
    }

    @Test
    public void TestGetAll() throws Exception {
        List<Supplier> list = new ArrayList();
        something.setId(1);
        something2.setId(2);
        list.add(something);
        list.add(something2);
        assertEquals(list, supplierTester.getAll());
    }
    @Test
    public void TestGetAllEmpty() throws Exception {
        supplierTester.remove(1);
        supplierTester.remove(2);
        List list = new ArrayList();
        assertEquals(list, supplierTester.getAll());
    }

}