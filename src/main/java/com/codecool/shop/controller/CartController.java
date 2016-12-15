package com.codecool.shop.controller;

import com.codecool.shop.model.ShoppingCart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CartController {

    private static Map params = new HashMap<>();

    public ModelAndView renderCart(Request req, Response res) {
        ShoppingCart cart = req.session().attribute("cart");
        try {
            params.put("lineItems", cart.getSessionItems());
            params.put("total", cart.getTotalPriceString());
            params.put("cartItemTotal", cart != null ? cart.totalQuantity() : null);
            return new ModelAndView(params, "cart/reviewcart");
        } catch (Exception e) {
            res.redirect(UrlController.getLastURL(req));
            return null;
        }
    }
}