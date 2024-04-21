package com.sopra.apirestcontroller.controller.mapper;

import java.util.List;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductEntity;

public interface iProductDtoMapper {
    ProductDto toProductDTO(ProductEntity productEntity);
    List<ProductDto> toProductsDTO(List<ProductEntity> productEntity);
    ProductEntity toProductEntity( ProductDto productDTO);
    List<ProductEntity> toProductEntities( List<ProductDto> productDto );
}
