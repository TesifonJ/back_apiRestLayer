package com.sopra.apirestcontroller.domain.persistance.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.controller.mapper.ProductTypeDtoMapperImpl;
import com.sopra.apirestcontroller.domain.persistance.DAO.iProductTypeDao;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductTypeEntity;
import com.sopra.apirestcontroller.domain.persistance.repository.iProductTypeRepository;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductTypeRepositoryImpl implements iProductTypeRepository {
    @Autowired
    iProductTypeDao oITypeDao;

    @Override
    public List<ProductTypeDto> getProductTypes() {
        List<ProductTypeEntity> dataBaseTypes = oITypeDao.findAll();
        List<ProductTypeDto> dtoTypes = new ArrayList<ProductTypeDto>();
        ProductTypeDtoMapperImpl oDtoMapperImpl = new ProductTypeDtoMapperImpl();

        for (ProductTypeEntity TypeEntity : dataBaseTypes) {
            dtoTypes.add(oDtoMapperImpl.toProductTypeDTO(TypeEntity));
        }
        return dtoTypes;
    }

    @Override
    public Optional<ProductTypeDto> getProductType(Long id) {
        ProductTypeDtoMapperImpl oDtoMapperImpl = new ProductTypeDtoMapperImpl();

        ProductTypeEntity dataBaseType = oITypeDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product Type " + id + " not found"));
        return Optional.of(oDtoMapperImpl.toProductTypeDTO(dataBaseType));
    }

    @Override
    public ProductTypeDto saveProductType(ProductTypeDto productType) {
        ProductTypeDtoMapperImpl oDtoMapperImpl = new ProductTypeDtoMapperImpl();

        ProductTypeEntity newTypeEntity = ProductTypeEntity.of(productType);
        oITypeDao.saveAndFlush(newTypeEntity);

        return oDtoMapperImpl.toProductTypeDTO(newTypeEntity);
    }

    @Override
    public Long deleteProductTypeById(Long id) {
        ProductTypeEntity TypeEntity = oITypeDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product Type " + id + " not found"));
        oITypeDao.deleteById(TypeEntity.getId());
        return TypeEntity.getId();
    }
}
