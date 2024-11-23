package com.example.cf;

import java.io.Serializable;

public class AmountDetails implements Serializable {
    private String amount;
    private String currency;
    private String category;

    // Constructor that accepts all the required details
    public AmountDetails(String amount, String currency, String category) {
        this.amount = amount;
        this.currency = currency;
        this.category = category;
    }

    // Getters and setters for all fields (optional, but useful if needed)
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
