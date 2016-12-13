package com.codecool.shop.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hamargyuri on 2016. 12. 13..
 */
public class RegistrationController {
    private static Map params = new HashMap<>();

    public static ModelAndView renderRegistration(Request req, Response res) {
        return new ModelAndView(params, "registration");
    }
}
