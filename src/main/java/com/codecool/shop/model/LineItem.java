package com.codecool.shop.model;

/**
 * Created by berloc on 2016.11.16..
 */
public class LineItem {

    private Product product;
    private Integer qty;

    public LineItem(){}

    public LineItem(Product product){
        product = product;
        qty = 1;
    }
}
