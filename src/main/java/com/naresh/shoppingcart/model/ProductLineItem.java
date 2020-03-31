package com.naresh.shoppingcart.model;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Holds the properties of Product,quantity,pricing info.
 */
public class ProductLineItem {
    @NotNull(message = "Product Cannot be null")
    @Valid
    private Product product;
    @Positive(message = "Quantity cannot be zero or negative")
    @NotNull(message = "Quantity cannot be empty")
    private Integer quantity;
    private BigDecimal totalPrice;
    private BigDecimal taxPriceApplied;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTaxPriceApplied() {
        return taxPriceApplied;
    }

    public void setTaxPriceApplied(BigDecimal taxPriceApplied) {
        this.taxPriceApplied = taxPriceApplied;
    }

    @Override
    public String toString() {
        return "ProductLineItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", taxPriceApplied=" + taxPriceApplied +
                '}';
    }
}
