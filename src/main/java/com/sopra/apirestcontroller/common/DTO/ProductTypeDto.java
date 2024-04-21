package com.sopra.apirestcontroller.common.DTO;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductTypeDto {
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    private Set<ProductTypeDto> products;

    public ProductTypeDto() {
        products = new HashSet<ProductTypeDto>();
    }

    public ProductTypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductTypeDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProducts() {
        return products.size();
    }
}
