package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by berloc on 2016.11.16..
 */
public class ShoppingCart implements Cart{

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

    public float totalPrice() {
        float total = 0;
        for (int i = 0; i < sessionItems.size(); i++) {
            total += sessionItems.get(i).getTotalPrice();
        }
        return total;
    }

    public String getTotalPriceString(){
        return String.valueOf(totalPrice()) + " " + getLineItem(1).getProduct().getDefaultCurrency().toString();
    }

    @Override
    public void addToCart(Product prod) {
        addToCart(prod, 1);
    }

    @Override
    public void addToCart(Product prod, Integer qty) {
        LineItem lineItem = new LineItem(prod, qty);
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
