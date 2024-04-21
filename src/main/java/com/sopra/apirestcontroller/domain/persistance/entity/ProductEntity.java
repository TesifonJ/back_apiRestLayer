package com.sopra.apirestcontroller.domain.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME", length = 50, nullable = false)
    String name;

    @Column(name = "PRODUCT_DESCRIPTION", length = 100)
    String description;

    @Column(name = "PRODUCT_PRICE")
    private float price;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ProductTypeEntity type;

    @Column(name = "PRODUCT_STOCK")
    private int stock;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String name, String description, ProductTypeEntity type, float price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public ProductEntity(String name, String description, ProductTypeEntity type, float price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    
    public ProductEntity(String name, String description, float price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductTypeEntity getType() {
        return type;
    }

    public void setType(ProductTypeEntity oProductType) {
        this.type = oProductType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idProduct) {
        this.id = idProduct;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
