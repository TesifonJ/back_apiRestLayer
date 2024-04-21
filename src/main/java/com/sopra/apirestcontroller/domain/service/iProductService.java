package com.sopra.apirestcontroller.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sopra.apirestcontroller.common.DTO.ProductDto;

@Service
public interface iProductService {
        public List<ProductDto> getAllProducts();

        public ProductDto findById(Long productId);

        public ProductDto create(ProductDto newProductDto);

        public ProductDto update(ProductDto productDto);

        public Long deleteById(Long id);

}
