package com.sopra.apirestcontroller.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductTypeEntity;

public class ProductTypeDtoMapperImpl implements iProductTypeDtoMapper {
    @Override
    public ProductTypeDto toProductTypeDTO(ProductTypeEntity productEntity) {
        ProductTypeDto productTypeDto = new ProductTypeDto();

        if (productEntity.getId() != null) {
            productTypeDto.setId(productEntity.getId());
        }
        productTypeDto.setName(productEntity.getName());

        return productTypeDto;
    }

    @Override
    public List<ProductTypeDto> toProductTypesDTO(List<ProductTypeEntity> productTypeEntities) {
        return productTypeEntities.stream()
                .map(productTypeEntity -> toProductTypeDTO(productTypeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ProductTypeEntity toProductTypeEntity(ProductTypeDto productTypeDTO) {
        ProductTypeEntity productEntity = new ProductTypeEntity();

        if (productTypeDTO.getId() != null) {
            productEntity.setId(productTypeDTO.getId());
        }

        productEntity.setName(productTypeDTO.getName());

        return productEntity;
    }

}
