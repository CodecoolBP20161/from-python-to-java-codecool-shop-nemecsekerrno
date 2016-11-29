package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by shevah on 28/11/16.
 */
public class SupplierDaoTest {


    Supplier something;
    Supplier something2;
    SupplierDao supplierTester;


    @Before
    public void setUp() throws Exception {

        something = new Supplier("test", "test");
        something2 = new Supplier("test2", "test2");
        supplierTester = SupplierDaoMem.getInstance();
    }
    @After
    public void tearDown() {
        supplierTester.clearAll();
    }


    @Test
    public void TestFindValidIndex() throws Exception {
        supplierTester.add(something);
        supplierTester.add(something2);
        assertEquals("test", supplierTester.find(1).getName());
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
        supplierTester.add(something);
        assertEquals(1, supplierTester.getAll().size());
    }
    @Test
    public void TestGetAllEmpty() throws Exception {
        List list = new ArrayList();
        assertEquals(list, supplierTester.getAll());
    }
}