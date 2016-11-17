package com.codecool.shop.model;

/**
 * Created by berloc on 2016.11.16..
 */
public class LineItem {
    private Product product;
    private Integer qty;

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getQty() {
        return qty;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public LineItem(){}

    public LineItem(Product prod){
        product = prod;
        qty = 1;
    }

    public float getTotalPrice(){
        return product.getPriceFloat()*qty;
    }

    public void increaseQty() {
        qty += 1;
    }
}
