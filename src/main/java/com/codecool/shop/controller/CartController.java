package com.codecool.shop.controller;

import com.codecool.shop.model.ShoppingCart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by reka on 2016.11.17..
 */
public class CartController {

    private static Map params = new HashMap<>();

    public static ModelAndView renderCart(Request req, Response res) {
        ShoppingCart cart = req.session().attribute("cart");
        try {
            params.put("lineItems", cart.getSessionItems());
            params.put("total", cart.getTotalPriceString());
            params.put("cartItemTotal", cart != null ? cart.totalQuantity() : null);
            return new ModelAndView(params, "cart/reviewcart");
        } catch (Exception e) {
            res.redirect(getLastURL(req));
            return null;
        }
    }

    private static String getLastURL(Request req) {
        String redirectURL;
        try {
            redirectURL = req.session().attribute("lastURL");
        } catch (NullPointerException e) {
            redirectURL = "/";
        }
        return redirectURL;
    }
}
