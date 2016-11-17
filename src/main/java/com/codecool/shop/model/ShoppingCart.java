package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by berloc on 2016.11.16..
 */
public class ShoppingCart {

    private List<LineItem> sessionItems = new ArrayList<>();

    public List<LineItem> getSessionItems() {
        return sessionItems;
    }

    public void setSessionItems(List<LineItem> sessionItems) {
        this.sessionItems = sessionItems;
    }

    public Integer totalQuantity(){
        Integer quantity = 0;
        for (int i = 0; i < sessionItems.size(); i++) {
            quantity += sessionItems.get(i).getQuantity();
        }
        return quantity;
    }

    public void addToCart(LineItem lineItem) {
        for (int i = 0; i < sessionItems.size(); i++) {
            if (sessionItems.get(i).getProduct() == lineItem.getProduct()) {
                sessionItems.get(i).increaseQty(lineItem.getQuantity());
                return;
            }
        }
        sessionItems.add(lineItem);
        return;
    }

    public LineItem getLineItem(int i){
        return sessionItems.get(i);
    }

}
