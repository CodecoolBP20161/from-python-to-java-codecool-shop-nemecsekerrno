package com.codecool.shop.model;

/**
 * Created by berloc on 2016.11.16..
 */
public class LineItem {
    private Product product;
    private Integer quantity;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public LineItem(){}

    public LineItem(Product prod, Integer qty){
        product = prod;
        quantity = qty;
    }

    public float getTotalPrice(){
        return product.getPriceFloat()* quantity;
    }

    public void increaseQty(Integer qty) {
        quantity += qty;
    }
}
