package com.sopra.apirestcontroller.domain.persistance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;

@Repository
public interface iProductTypeRepository {
    List<ProductTypeDto> getProductTypes();

    Optional<ProductTypeDto> getProductType(Long id);

    ProductTypeDto saveProductType(ProductTypeDto productType);

    Long deleteProductTypeById(Long id);
}
