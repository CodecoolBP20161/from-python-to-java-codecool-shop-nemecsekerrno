package com.codecool.shop;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.SQLException;


public class PopulateData {

    public static void main(String[] args) throws SQLException {

        ProductDao productDataStore = new ProductDaoJdbc();
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJdbc();
        SupplierDao supplierDataStore = new SupplierDaoJdbc();

        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        amazon = supplierDataStore.find(1);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        lenovo = supplierDataStore.find(2);
        Supplier apple = new Supplier("Apple", "Computers");
        supplierDataStore.add(apple);
        apple = supplierDataStore.find(3);
        Supplier samsung = new Supplier("Samsung", "Electronics");
        supplierDataStore.add(samsung);
        samsung = supplierDataStore.find(4);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        tablet = productCategoryDataStore.find(1);
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "A notebook for people that is very nice and useful.");
        productCategoryDataStore.add(notebook);
        notebook = productCategoryDataStore.find(2);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A phone to talk");
        productCategoryDataStore.add(phone);
        phone = productCategoryDataStore.find(3);

        //setting up products and printing it
        System.out.println(samsung.getId());
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Lenovo 310-15IKB 15.6 Laptop", 390, "USD", "Intel Core i5 - 8GB Memory - 1TB Hard Drive", notebook, lenovo));
        productDataStore.add(new Product("Apple MacBook Air® 13.3", 950, "USD", "Intel Core i5 - 8GB Memory - 128GB Flash Storage - Silver", notebook, apple));
        productDataStore.add(new Product("Lenovo Ideapad 110s 11.6", 220, "USD", "Intel Celeron - 2GB Memory - 32GB eMMC Flash Memory", notebook, lenovo));
        productDataStore.add(new Product("Samsung Chromebook 3 - 11.6", 185, "USD", "Intel Celeron - 4GB Memory - 16GB eMMC flash memory", notebook, samsung));
        productDataStore.add(new Product("iPhone 5S", 289, "USD", "64 GB Fantastic iPhone5, everybody wants this.", phone, apple));
        productDataStore.add(new Product("iPhone 6 ", 400, "USD", "64 GB Fantastic iPhone6, everybody wants this.", phone, apple));
        productDataStore.add(new Product("iPhone SE", 500, "USD", "64 GB Retail Packaging - Space Gray", phone, apple));
        productDataStore.add(new Product("Samsung Galaxy S6 G920V", 270, "USD", "64GB Android Smartphone - Black Sapphire - Verizon", phone, samsung));
        productDataStore.add(new Product("Samsung Galaxy S6 Edge+ Plus", 647, "USD", "Fantastic Black Saphire Factory", phone, samsung));
        productDataStore.add(new Product("Samsung Galaxy S7 Edge", 689, "USD", "Fantastic Black smartphone", phone, samsung));

    }
}
