package com.naresh.shoppingcart.model;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;


public class Product {
    @NotNull(message = "Product id cannot be null")
    private String id;
    private String name;
    @NotNull(message = "Product price cannot be null")
    @PositiveOrZero(message = "Product price cannot be negative")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
