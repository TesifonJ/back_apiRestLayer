package com.sopra.apirestcontroller.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sopra.apirestcontroller.common.DTO.ProductDto;
import com.sopra.apirestcontroller.controller.ProductController;
import com.sopra.apirestcontroller.domain.service.Impl.ProductServiceImpl;

@SpringBootTest
public class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(productService);
        productController = new ProductController(productService);
    }

    @Test
    public void testGetAllProducts() {
        List<ProductDto> products = Arrays.asList(new ProductDto(), new ProductDto());
        Mockito.when(productService.getAllProducts()).thenReturn(products);

        List<ProductDto> result = productController.getAll();

        assertEquals(2, result.size());
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void testGetProduct() {
        Long id = 1L;
        ProductDto product = new ProductDto();
        Mockito.when(productService.findById(id)).thenReturn(product);

        ProductDto result = productController.getProduct(id).getBody();

        assertNotNull(result);
        Mockito.verify(productService, Mockito.times(1)).findById(id);
    }

    @Test
    public void testPostCreate() {
        ProductDto product = new ProductDto();
        Mockito.when(productService.create(product)).thenReturn(product);
        ResponseEntity<ProductDto> result = productController.postCreate(product);

        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK.value(),result.getStatusCode().value());
        Mockito.verify(productService, Mockito.times(1)).create(product);
    }

    @Test
    public void testPutUpdate() {
        ProductDto product = new ProductDto();
        Mockito.when(productService.update(product)).thenReturn(product);

        ResponseEntity<ProductDto> result = productController.putUpdate(product);

        assertNotNull(result.getBody());
        assertEquals(HttpStatus.OK.value(),result.getStatusCode().value());
        Mockito.verify(productService, Mockito.times(1)).update(product);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Mockito.when(productService.deleteById(id)).thenReturn(id);

        Long result = productController.deleteDelete(id).getBody();

        assertNotNull(result);
        assertEquals(id, result);
        Mockito.verify(productService, Mockito.times(1)).deleteById(id);
    }

}
