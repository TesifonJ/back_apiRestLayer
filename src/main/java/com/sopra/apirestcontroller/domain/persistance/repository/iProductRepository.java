package com.sopra.apirestcontroller.domain.persistance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.common.DTO.ProductDto;

@Repository
public interface iProductRepository {
    List<ProductDto> getProducts();
    Optional <ProductDto> getProduct (Long id);
    ProductDto saveProduct (ProductDto product);
    ProductDto updateProduct (ProductDto product);
    Long deleteProductById(Long id);
}
