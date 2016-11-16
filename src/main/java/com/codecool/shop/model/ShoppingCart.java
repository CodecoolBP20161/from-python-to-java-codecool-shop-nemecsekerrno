package com.codecool.shop.model;

import java.util.Map;

/**
 * Created by berloc on 2016.11.16..
 */
public class ShoppingCart {

    private Map<Integer, Integer> sessionItems;

    public Integer totalQuantity(){
        int quantity=0;
        for (Integer value : sessionItems.values()) {
            quantity += value;
        }
        return quantity;
    }

    public void add(Integer id, Integer quantity) {
        if (sessionItems.containsKey(id)) {
            sessionItems.put(id, sessionItems.get(id)+quantity);
        } else {
            sessionItems.put(id, quantity);
        }
    }

    public Map getAll() {
        return sessionItems;
    }
}
