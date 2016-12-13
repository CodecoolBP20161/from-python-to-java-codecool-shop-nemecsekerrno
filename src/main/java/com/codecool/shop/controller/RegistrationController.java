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
            PasswordStorage.CannotPerformOperationException {
        System.out.println(req.attributes());
        String first_name = req.queryParams("c_first_name");
        String last_name = req.queryParams("c_last_name");
        String email = req.queryParams("c_email");
        String pw = PasswordController.hashPassword(req.queryParams("c_pw"));
        Customer test = new Customer(first_name, last_name, email, pw);
        try {
            CustomerHandler.add(test);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return renderRegistration(req, res);
    }

    public static ModelAndView renderRegistration(Request req, Response res) {
        return new ModelAndView(params, "registration");
    }
}
