package com.sopra.apirestcontroller.domain.persistance.entity;

import java.util.HashSet;
import java.util.Set;

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "types")
public class ProductTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Set<ProductEntity> products;

    public ProductTypeEntity() {
        products = new HashSet<ProductEntity>();
    }

    public ProductTypeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductTypeEntity(String name) {
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

    public int getProductsSize() {
        return products.size();
    }

    public static ProductTypeEntity of(ProductTypeDto oProductTypeDto) {
        ProductTypeEntity oProductTypeEntity = new ProductTypeEntity();
        if (oProductTypeDto.getId() != null) {
            oProductTypeEntity.setId(oProductTypeDto.getId());
        }

        if (oProductTypeDto.getName() != null) {
            oProductTypeEntity.setName(oProductTypeDto.getName());
        }
        
        return oProductTypeEntity;
    }

}
