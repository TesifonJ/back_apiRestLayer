package com.sopra.apirestcontroller.domain.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.ProductRepositoryImpl;
import com.sopra.apirestcontroller.domain.service.iProductService;


@Service
public class ProductServiceImpl implements iProductService {
    ProductRepositoryImpl iProductRepository;

    @Autowired
    public ProductServiceImpl(ProductRepositoryImpl iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return this.iProductRepository.getProducts();
    }

    @Override
    public ProductDto findById(Long productId) {
        return this.iProductRepository.getProduct(productId).get();
    }

    @Override
    public ProductDto create(ProductDto newProductDto) {
        return this.iProductRepository.saveProduct(newProductDto);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        if (findById(productDto.getId()) == null) {
            throw new RuntimeException("Product " + productDto.getId() + " not found");
        }
        return this.iProductRepository.updateProduct(productDto);
    }

    @Override
    public Long deleteById(Long id) {
        return this.iProductRepository.deleteProductById(id);
    }

}
