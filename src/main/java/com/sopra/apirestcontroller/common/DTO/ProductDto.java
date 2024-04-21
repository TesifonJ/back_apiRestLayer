package com.sopra.apirestcontroller.common.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductDto {
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    @Size(min = 3, max = 255)
    private String description;

    @DecimalMax(value = "99999999.99", message = "The price value must contain only two decimal")
    @PositiveOrZero
    private float price;

    private ProductTypeDto type;

    @PositiveOrZero
    private int stock;

    public ProductDto() {
    }

    public ProductDto(String name, String description, ProductTypeDto type, float price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public ProductDto(Long id, String name, String description, float price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public ProductDto(Long id, String name, String description, ProductTypeDto type, float price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setType(ProductTypeDto type) {
        this.type = type;
    }

    public ProductTypeDto getType() {
        return type;
    }

}
