package com.codecool.shop.model;

/**
 * Created by reka on 2016.11.17..
 */
public interface Cart {

    void addToCart(Product prod);
    void addToCart(Product prod, Integer qty);

}
