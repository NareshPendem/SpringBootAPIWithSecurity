package com.naresh.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

/**
 * Checkout API Incoming Request.
 */
public class CartDataRequest {
    @JsonProperty("items")
    @NotNull(message = "Items cannot be null")
    @NotEmpty(message = "Items cannot be empty")
    @Valid
    private List<ProductLineItem> productLineItems;
    @PositiveOrZero(message = "TaxRate cannot be negative")
    @NotNull(message = "TaxRate cannot be null")
    private BigDecimal taxRate;
    @PositiveOrZero(message = "CurrencyRate cannot be negative")
    @NotNull(message = "CurrencyRate cannot be null")
    private BigDecimal currencyRate;

    public List<ProductLineItem> getProductLineItems() {
        return productLineItems;
    }

    public void setProductLineItems(List<ProductLineItem> productLineItems) {
        this.productLineItems = productLineItems;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    @Override
    public String toString() {
        return "CartDataRequest{" +
                "productLineItems=" + productLineItems +
                ", taxRate=" + taxRate +
                ", currencyRate=" + currencyRate +
                '}';
    }
}
