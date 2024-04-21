package com.sopra.apirestcontroller.repositoryImplTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.controller.mapper.ProductDtoMapperImpl;
import com.sopra.apirestcontroller.controller.mapper.ProductTypeDtoMapperImpl;
import com.sopra.apirestcontroller.domain.persistance.DAO.iProductDao;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductEntity;
import com.sopra.apirestcontroller.domain.persistance.entity.ProductTypeEntity;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.ProductRepositoryImpl;

@SpringBootTest
public class ProductRepositoryImplTest {
    @Mock
    private iProductDao oIProductDao;

    @InjectMocks
    private ProductRepositoryImpl oProductRepositoryImpl;

    @Mock
    private ProductDtoMapperImpl oProductDtoMapperImpl;
    @Mock
    private ProductTypeDtoMapperImpl oProductTypeDtoMapperImpl;

    private final Long DEFAULT_PRODUCT_ID = 1L;
    private final String DEFAULT_PRODUCT_NAME = "Name";
    private final String DEFAULT_PRODUCT_DESCRIPTION = "Description";
    private final float DEFAULT_PRODUCT_PRICE = 99.99f;
    private final int DEFAULT_PRODUCT_STOCK = 99;

    private final ProductTypeEntity DEFAULT_PRODUCT_TYPE_ENTITY = new ProductTypeEntity(1L, "Type");
    private final ProductTypeDto DEFAULT_PRODUCT_TYPE_DTO = new ProductTypeDto(1L, "Type");

    private final ProductEntity DEFAULT_PRODUCT_ENTITY = new ProductEntity(DEFAULT_PRODUCT_ID, DEFAULT_PRODUCT_NAME,
            DEFAULT_PRODUCT_DESCRIPTION,
            DEFAULT_PRODUCT_TYPE_ENTITY, DEFAULT_PRODUCT_PRICE, DEFAULT_PRODUCT_STOCK);

    private final ProductDto DEFAULT_PRODUCT_DTO = new ProductDto(DEFAULT_PRODUCT_ID, DEFAULT_PRODUCT_NAME,
            DEFAULT_PRODUCT_DESCRIPTION, DEFAULT_PRODUCT_TYPE_DTO, DEFAULT_PRODUCT_PRICE, DEFAULT_PRODUCT_STOCK);

    @Test
    public void testGetAllProducts() {
        // Arr
        final int EXPECTED_SIZE = 2;
        List<ProductEntity> productsDto = Arrays.asList(DEFAULT_PRODUCT_ENTITY,DEFAULT_PRODUCT_ENTITY);
        Mockito.when(oIProductDao.findAll()).thenReturn(productsDto);
        // Act
        List<ProductDto> result = oProductRepositoryImpl.getProducts();
        // Ass
        assertAll(
                () -> assertEquals(EXPECTED_SIZE, result.size()),
                () -> assertEquals(DEFAULT_PRODUCT_ID, result.get(0).getId()),
                () -> assertEquals(DEFAULT_PRODUCT_NAME, result.get(0).getName()),
                () -> assertEquals(DEFAULT_PRODUCT_DESCRIPTION, result.get(0).getDescription()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getId(), result.get(0).getType().getId()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getName(), result.get(0).getType().getName()),
                () -> assertEquals(DEFAULT_PRODUCT_PRICE, result.get(0).getPrice()),
                () -> assertEquals(DEFAULT_PRODUCT_STOCK, result.get(0).getStock()));
    }

    @Test
    public void testFindById() {
        // Arr
        Mockito.when(oIProductDao.findById(DEFAULT_PRODUCT_ID)).thenReturn(Optional.of(DEFAULT_PRODUCT_ENTITY));
        // Act
        ProductDto result = oProductRepositoryImpl.getProduct(DEFAULT_PRODUCT_ID).get();
        // Ass
        assertAll(
                () -> assertEquals(DEFAULT_PRODUCT_ID, result.getId()),
                () -> assertEquals(DEFAULT_PRODUCT_NAME, result.getName()),
                () -> assertEquals(DEFAULT_PRODUCT_DESCRIPTION, result.getDescription()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getId(), result.getType().getId()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getName(), result.getType().getName()),
                () -> assertEquals(DEFAULT_PRODUCT_PRICE, result.getPrice()),
                () -> assertEquals(DEFAULT_PRODUCT_STOCK, result.getStock()),
                () -> Mockito.verify(oIProductDao, Mockito.times(1)).findById(DEFAULT_PRODUCT_ID)
                );
    }

    @Test
    public void testCreateCorrectAttributes() {
        // Arr
        Mockito.when(oIProductDao.findById(DEFAULT_PRODUCT_ID))
                .thenReturn(Optional.of(DEFAULT_PRODUCT_ENTITY));
        Mockito.when(oIProductDao.saveAndFlush(DEFAULT_PRODUCT_ENTITY))
                .thenReturn(DEFAULT_PRODUCT_ENTITY);
        // Act
        ProductDto result = oProductRepositoryImpl.saveProduct(DEFAULT_PRODUCT_DTO);
        // Ass
        assertAll(
                () -> assertEquals(DEFAULT_PRODUCT_ID, result.getId()),
                () -> assertEquals(DEFAULT_PRODUCT_NAME, result.getName()),
                () -> assertEquals(DEFAULT_PRODUCT_DESCRIPTION, result.getDescription()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getId(), result.getType().getId()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getName(), result.getType().getName()),
                () -> assertEquals(DEFAULT_PRODUCT_PRICE, result.getPrice()),
                () -> assertEquals(DEFAULT_PRODUCT_STOCK, result.getStock()),
                () -> Mockito.verify(oIProductDao, Mockito.times(1)).saveAndFlush(Mockito.any(ProductEntity.class)),
                () -> Mockito.verify(oIProductDao, Mockito.times(1)).findById(DEFAULT_PRODUCT_ID));
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Mockito.when(oIProductDao.findById(DEFAULT_PRODUCT_DTO.getId()))
                .thenReturn(Optional.of(DEFAULT_PRODUCT_ENTITY));
        Mockito.when(oProductDtoMapperImpl.toProductEntity(DEFAULT_PRODUCT_DTO))
                .thenReturn(DEFAULT_PRODUCT_ENTITY);
        Mockito.when(oIProductDao.saveAndFlush(DEFAULT_PRODUCT_ENTITY)).thenReturn(DEFAULT_PRODUCT_ENTITY);

        // Act
        ProductDto result = oProductRepositoryImpl.updateProduct(DEFAULT_PRODUCT_DTO);
        // Assert
        assertAll(
                () -> assertEquals(DEFAULT_PRODUCT_ID, result.getId()),
                () -> assertEquals(DEFAULT_PRODUCT_NAME, result.getName()),
                () -> assertEquals(DEFAULT_PRODUCT_DESCRIPTION, result.getDescription()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getId(), result.getType().getId()),
                () -> assertEquals(DEFAULT_PRODUCT_TYPE_DTO.getName(), result.getType().getName()),
                () -> assertEquals(DEFAULT_PRODUCT_PRICE, result.getPrice()),
                () -> assertEquals(DEFAULT_PRODUCT_STOCK, result.getStock()),
                () -> Mockito.verify(oIProductDao, Mockito.times(1)).saveAndFlush(Mockito.any(ProductEntity.class)),
                () -> Mockito.verify(oIProductDao, Mockito.times(1)).findById(DEFAULT_PRODUCT_ID));
    }

    @Test
    public void testDeleteProductById() {
        // Arr
        ProductEntity EXPECTED_PRODUCT_ENTITY = DEFAULT_PRODUCT_ENTITY;
        Mockito.when(oIProductDao.findById(Mockito.anyLong())).thenReturn(Optional.of(EXPECTED_PRODUCT_ENTITY));
        Mockito.doNothing().when(oIProductDao).deleteById(anyLong());
        // Act
        Long deletedProductId = oProductRepositoryImpl.deleteProductById(DEFAULT_PRODUCT_ID);

        // Assert
        assertEquals(DEFAULT_PRODUCT_ID, deletedProductId);
    }
}
