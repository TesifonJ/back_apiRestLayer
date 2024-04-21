package com.sopra.apirestcontroller.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductEntity;

public class ProductDtoMapperImpl implements iProductDtoMapper {

    @Override
    public ProductDto toProductDTO(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        ProductTypeDtoMapperImpl oDtoTypeMapperImpl = new ProductTypeDtoMapperImpl();

        if (productEntity.getId() != null) {
            productDto.setId(productEntity.getId());
        }
        productDto.setName(productEntity.getName());
        productDto.setDescription(productEntity.getDescription());
        productDto.setPrice(productEntity.getPrice());
        productDto.setStock(productEntity.getStock());
        if (productEntity.getType() != null) {
            productDto.setType(oDtoTypeMapperImpl.toProductTypeDTO(productEntity.getType()));
        }
        return productDto;
    }

    @Override
    public List<ProductDto> toProductsDTO(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(productEntity -> toProductDTO(productEntity))
                .collect(Collectors.toList());
    }

    @Override
    public ProductEntity toProductEntity(ProductDto productDTO) {
        ProductEntity productEntity = new ProductEntity();
        ProductTypeDtoMapperImpl oDtoTypeMapperImpl = new ProductTypeDtoMapperImpl();

        if (productDTO.getId() != null) {
            productEntity.setId(productDTO.getId());
        }

        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStock(productDTO.getStock());

        if (productDTO.getType() != null) {
            productEntity.setType(oDtoTypeMapperImpl.toProductTypeEntity(productDTO.getType()));
        }
        return productEntity;
    }

    @Override
    public List<ProductEntity> toProductEntities(List<ProductDto> productsDto) {
        return productsDto.stream()
                .map(productDto -> toProductEntity(productDto))
                .collect(Collectors.toList());
    }
}
