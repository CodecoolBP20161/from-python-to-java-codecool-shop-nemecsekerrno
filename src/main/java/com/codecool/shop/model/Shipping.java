package com.codecool.shop.model;

import java.util.Currency;

public class Shipping extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;

    public Shipping(String name, String description, float defaultPrice, String currencyString) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
    }

    private void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
}
