package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.CustomerDaoJdbc;
import com.codecool.shop.model.Customer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hamargyuri on 2016. 12. 13..
 */
public class RegistrationController {
    private static Map params = new HashMap<>();
    private static CustomerDaoJdbc CustomerHandler = new CustomerDaoJdbc();


    public static ModelAndView handleRegistration(Request req, Response res) throws
            PasswordStorage.CannotPerformOperationException, SQLException {
        String first_name = req.queryParams("c_first_name");
        String last_name = req.queryParams("c_last_name");
        String email = req.queryParams("c_email");
        String pw = PasswordController.hashPassword(req.queryParams("c_pw"));

        if (CustomerHandler.isNewUser(email)) {
            Customer test = new Customer(first_name, last_name, email, pw);
            CustomerHandler.add(test);
            return ProductController.renderAllProducts(req, res);
        } else {
            return renderWithBadEmail(req, res, first_name, last_name);
        }

    }

    public static ModelAndView renderRegistration(Request req, Response res) {
        return new ModelAndView(params, "registration");
    }

    public static ModelAndView renderWithBadEmail(Request req, Response res, String fname, String lname) {
        params.put("fname", fname);
        params.put("lname", lname);
        params.put("email_err", "email_err");
        return new ModelAndView(params, "registration");
    }

}
