package com.codecool.shop.controller;

import com.codecool.shop.model.Shipping;
import com.codecool.shop.model.ShoppingCart;
import com.sun.org.apache.xpath.internal.operations.Mod;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class CartController {

    private static Map params = new HashMap<>();
    private ShoppingCart cart;

    public ModelAndView renderCart(Request req, Response res) {
        if (cart == null) {
            cart = req.session().attribute("cart");
        }
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

    public ModelAndView renderCartWithShipping(Request req, Response res) {
        cart = req.session().attribute("cart");
        float shippingCost = Float.parseFloat(req.queryParams("shippingcost"));
        try {
            if (cart.getShippingOption() == null) {
                Shipping shipping = new Shipping("Shipping", "Shipping", shippingCost, "USD");
                cart.addShipping(shipping);
            } else {
                cart.getShippingOption().setDefaultPrice(shippingCost);
            }
            params.put("shipping", cart.getShippingOption());
            return renderCart(req, res);
        } catch (Exception e) {
            res.redirect(UrlController.getLastURL(req));
            return null;
        }
    }
}