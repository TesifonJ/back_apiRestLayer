package com.sopra.apirestcontroller.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.controller.mapper.ProductTypeDtoMapperImpl;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductTypeEntity;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.ProductRepositoryImpl;
import com.sopra.apirestcontroller.domain.service.Impl.ProductServiceImpl;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepositoryImpl productRepository;

    private ProductTypeDtoMapperImpl oTypeDtoMapperImpl;

    private ProductServiceImpl oProductServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(productRepository);
        oProductServiceImpl = new ProductServiceImpl(productRepository);
        oTypeDtoMapperImpl = new ProductTypeDtoMapperImpl();
    }

    @Test
    public void testGetAllProducts() {
        ProductTypeEntity oTypeEntity = new ProductTypeEntity();
        ProductTypeDto oProductTypeDto = oTypeDtoMapperImpl.toProductTypeDTO(oTypeEntity);
        ProductDto oProductDto = new ProductDto(1L, "Name", "Description", oProductTypeDto, 32, 43);
        List<ProductDto> products = Arrays.asList(oProductDto, oProductDto);

        Mockito.when(productRepository.getProducts()).thenReturn(products);

        List<ProductDto> result = oProductServiceImpl.getAllProducts();

        assertEquals(2, result.size());
        Mockito.verify(productRepository, Mockito.times(1)).getProducts();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        ProductTypeEntity oTypeEntity = new ProductTypeEntity();
        ProductTypeDto oProductTypeDto = oTypeDtoMapperImpl.toProductTypeDTO(oTypeEntity);
        ProductDto oProductDto = new ProductDto(1L, "Name", "Description", oProductTypeDto, 32, 43);

        Mockito.when(productRepository.getProduct(id)).thenReturn(Optional.of(oProductDto));
        ProductDto result = oProductServiceImpl.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        Mockito.verify(productRepository, Mockito.times(1)).getProduct(id);
    }

    @Test
    public void testFindByIdCorrectAttributes() {
        Long id = 1L;
        ProductTypeEntity oTypeEntity = new ProductTypeEntity("Food");
        ProductTypeDto oProductTypeDto = oTypeDtoMapperImpl.toProductTypeDTO(oTypeEntity);
        ProductDto oProductDto = new ProductDto(1L, "Name", "Description", oProductTypeDto, 32, 43);

        Mockito.when(productRepository.getProduct(id)).thenReturn(Optional.of(oProductDto));
        ProductDto result = oProductServiceImpl.findById(id);

        assertNotNull(result);
        assertEquals(oProductDto.getId(), result.getId());
        assertEquals(oProductDto.getName(), result.getName());
        assertEquals(oProductDto.getDescription(), result.getDescription());
        assertEquals(oProductDto.getType().getName(), result.getType().getName());
        assertEquals(oProductDto.getPrice(), result.getPrice());
        assertEquals(oProductDto.getStock(), result.getStock());
    }

    @Test
    public void testCreateCorrectAttributes() {
        Long id = 1L;
        ProductTypeEntity oTypeEntity = new ProductTypeEntity("Food");
        ProductTypeDto oProductTypeDto = oTypeDtoMapperImpl.toProductTypeDTO(oTypeEntity);
        ProductDto oProductDto = new ProductDto(1L, "Name", "Description", oProductTypeDto, 32, 43);

        Mockito.when(productRepository.saveProduct(oProductDto)).thenReturn(oProductDto);

        ProductDto result = oProductServiceImpl.create(oProductDto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        Mockito.verify(productRepository, Mockito.times(1)).saveProduct(oProductDto);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        ProductTypeDto oTypeDto = new ProductTypeDto("Drink");
        ProductDto bodyRequestProductDto = new ProductDto(1L, "newName", "newDescription", oTypeDto, 5f, 5);
        ProductDto foundProductDto = new ProductDto(1L, "newName", "newDescription", oTypeDto, 5f, 5);

        Mockito.when(productRepository.saveProduct(bodyRequestProductDto)).thenReturn(bodyRequestProductDto);
        Mockito.when(productRepository.getProduct(id)).thenReturn(Optional.of(foundProductDto));

        ProductDto result = oProductServiceImpl.update(bodyRequestProductDto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        Mockito.verify(productRepository, Mockito.times(1)).saveProduct(bodyRequestProductDto);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        Mockito.when(productRepository.deleteProductById(id)).thenReturn(id);
        Long result = oProductServiceImpl.deleteById(id);
        
        assertEquals(id, result);
        Mockito.verify(productRepository, Mockito.times(1)).deleteProductById(id);
    }
}
