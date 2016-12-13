import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.CartController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.RegistrationController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
//        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        // Always add generic routes to the end
        get("/", ProductController::renderAllProducts, new ThymeleafTemplateEngine());

        // dynamic route for categories
        get("/category/:id", ProductController::renderProductsByCategory, new ThymeleafTemplateEngine());

        get("/supplier/:id", ProductController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        get("/cart/add_product/:prodID", ProductController::handleAddToCart, new ThymeleafTemplateEngine());

        get("/cart/review", CartController::renderCart, new ThymeleafTemplateEngine());

        get("/registration", RegistrationController::renderRegistration, new ThymeleafTemplateEngine());

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Computers");
        supplierDataStore.add(apple);
        Supplier samsung = new Supplier("Samsung", "Electronics");
        supplierDataStore.add(samsung);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "A notebook for people that is very nice and useful.");
        productCategoryDataStore.add(notebook);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A phone to talk");
        productCategoryDataStore.add(phone);

        //setting up products and printing it
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
