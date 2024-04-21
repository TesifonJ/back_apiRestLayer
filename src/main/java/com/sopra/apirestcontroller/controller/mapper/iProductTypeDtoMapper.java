package com.sopra.apirestcontroller.controller.mapper;

import java.util.List;

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductTypeEntity;

public interface iProductTypeDtoMapper {
    ProductTypeDto toProductTypeDTO(ProductTypeEntity productEntity);

    List<ProductTypeDto> toProductTypesDTO(List<ProductTypeEntity> productEntity);

    ProductTypeEntity toProductTypeEntity(ProductTypeDto productDTO);
}
