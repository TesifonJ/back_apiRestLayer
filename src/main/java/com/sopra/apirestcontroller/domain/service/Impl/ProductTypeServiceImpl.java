package com.sopra.apirestcontroller.domain.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.ProductTypeRepositoryImpl;
import com.sopra.apirestcontroller.domain.service.iProductTypeService;

@Service
public class ProductTypeServiceImpl implements iProductTypeService {

    private ProductTypeRepositoryImpl iTypeRepository;
    public ProductTypeServiceImpl(){
        
    }
    @Autowired
    public ProductTypeServiceImpl(ProductTypeRepositoryImpl iTypeRepository) {
        this.iTypeRepository = iTypeRepository;
    }

    @Override
    public List<ProductTypeDto> getAllTypes() {
        return this.iTypeRepository.getProductTypes();
    }

    @Override
    public ProductTypeDto findById(Long typeId) {
        return this.iTypeRepository.getProductType(typeId).get();
    }

    @Override
    public ProductTypeDto create(ProductTypeDto newTypeDto) {
        return this.iTypeRepository.saveProductType(newTypeDto);
    }

    // New error
    @Override
    public ProductTypeDto update(ProductTypeDto typeDto) {
        if (findById(typeDto.getId()) == null) {
            throw new RuntimeException("Product type " + typeDto.getId() + " not found");
        }
        return this.iTypeRepository.saveProductType(typeDto);
    }

    @Override
    public Long deleteById(Long id) {
        return this.iTypeRepository.deleteProductTypeById(id);
    }

}
