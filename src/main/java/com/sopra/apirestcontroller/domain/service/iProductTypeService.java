package com.sopra.apirestcontroller.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;

@Service
public interface iProductTypeService {

        public List<ProductTypeDto> getAllTypes();

        public ProductTypeDto findById(Long typeId);

        public ProductTypeDto create(ProductTypeDto newTypeDto);

        public ProductTypeDto update(ProductTypeDto typeDto);

        public Long deleteById(Long id);
}
