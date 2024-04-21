package com.sopra.apirestcontroller.domain.persistance.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.controller.mapper.ProductDtoMapperImpl;
import com.sopra.apirestcontroller.domain.persistance.DAO.iProductDao;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductEntity;
import com.sopra.apirestcontroller.domain.persistance.repository.iProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductRepositoryImpl implements iProductRepository {

    iProductDao oIProductDao;

    @Autowired
    public ProductRepositoryImpl(iProductDao oIProductDao) {
        this.oIProductDao = oIProductDao;
    }

    @Override
    public List<ProductDto> getProducts() {
        List<ProductEntity> dataBaseProducts = oIProductDao.findAll();
        List<ProductDto> dtoProducts = new ArrayList<ProductDto>();
        ProductDtoMapperImpl oDtoMapperImpl = new ProductDtoMapperImpl();

        for (ProductEntity productEntity : dataBaseProducts) {
            dtoProducts.add(oDtoMapperImpl.toProductDTO(productEntity));
        }
        return dtoProducts;
    }

    @Override
    public Optional<ProductDto> getProduct(Long productId) {
        ProductDtoMapperImpl oDtoMapperImpl = new ProductDtoMapperImpl();

        ProductEntity dataBaseProduct = oIProductDao.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product " + productId + " not found"));
        return Optional.of(oDtoMapperImpl.toProductDTO(dataBaseProduct));
    }

    @Override
    public ProductDto saveProduct(ProductDto newProductDto) {
        ProductDtoMapperImpl oDtoMapperImpl = new ProductDtoMapperImpl();
        ProductEntity newProductEntity = oDtoMapperImpl.toProductEntity(newProductDto);

        oIProductDao.saveAndFlush(newProductEntity);

        return getProduct(newProductEntity.getId()).get();
    }

    @Override
    public Long deleteProductById(Long id) {
        ProductEntity productEntity = oIProductDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product " + id + " not found"));
        oIProductDao.deleteById(productEntity.getId());
        return productEntity.getId();
    }

    @Override
    public ProductDto updateProduct(ProductDto updatedProduct) {
        ProductDto currentDBProductToUpdate = getProduct(updatedProduct.getId()).get();
        ProductDtoMapperImpl oDtoMapperImpl = new ProductDtoMapperImpl();

        currentDBProductToUpdate.setId(updatedProduct.getId());
        currentDBProductToUpdate.setName(updatedProduct.getName());
        currentDBProductToUpdate.setDescription(updatedProduct.getDescription());
        currentDBProductToUpdate.setPrice(updatedProduct.getPrice());
        currentDBProductToUpdate.setStock(updatedProduct.getStock());
        currentDBProductToUpdate.setType(updatedProduct.getType());

        oIProductDao.saveAndFlush(oDtoMapperImpl.toProductEntity(currentDBProductToUpdate));

        return currentDBProductToUpdate;

    }

}
