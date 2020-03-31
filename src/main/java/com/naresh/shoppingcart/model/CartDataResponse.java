package com.naresh.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CartDataResponse {
    @JsonProperty("items")
    private List<ProductLineItem> productLineItems;
    private String id = UUID.randomUUID().toString();
    private BigDecimal totalCartPrice;
    private BigDecimal totalTaxApplied;
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<ProductLineItem> getProductLineItems() {
        return productLineItems;
    }

    public void setProductLineItems(List<ProductLineItem> productLineItems) {
        this.productLineItems = productLineItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(BigDecimal totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    public BigDecimal getTotalTaxApplied() {
        return totalTaxApplied;
    }

    public void setTotalTaxApplied(BigDecimal totalTaxApplied) {
        this.totalTaxApplied = totalTaxApplied;
    }
}
