package com.codecool.shop.controller;

import spark.Request;

/**
 * Created by berloc on 2016.11.17..
 */
abstract class UrlController {

    static void saveLastURL(Request req) {
        req.session().attribute("lastURL", req.pathInfo());
    }


    static String getLastURL(Request req) {
        String redirectURL;
        try {
            redirectURL = req.session().attribute("lastURL");
        } catch (NullPointerException e) {
            redirectURL = "/";
        }
        return redirectURL;
    }

}
