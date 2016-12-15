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
    private static EmailSender emailSender = new EmailSender();


    public ModelAndView handleRegistration(Request req, Response res) throws
            PasswordHandler.CannotPerformOperationException, SQLException {
        String firstName = req.queryParams("firstname");
        String lastName = req.queryParams("lastname");
        String email = req.queryParams("email");
        String pw = PasswordController.hashPassword(req.queryParams("password"));
        if (CustomerHandler.isNewUser(email)) {
            Customer test = new Customer(firstName, lastName, email, pw);
            CustomerHandler.add(test);
            emailSender.sendEmail(email, firstName, lastName);
            return renderConfirmation(req, res);
        } else {
            return renderWithBadEmail(req, res, firstName, lastName);
        }

    }

    public ModelAndView renderRegistration(Request req, Response res) {
        return new ModelAndView(params, "registration/registration");
    }

    public ModelAndView renderWithBadEmail(Request req, Response res, String fname, String lname) {
        params.put("fname", fname);
        params.put("lname", lname);
        params.put("email_err", "email_err");
        return new ModelAndView(params, "registration/registration");
    }

    public ModelAndView renderConfirmation(Request req, Response res) {
        return new ModelAndView(params, "registration/registration_conf");
    }

}
