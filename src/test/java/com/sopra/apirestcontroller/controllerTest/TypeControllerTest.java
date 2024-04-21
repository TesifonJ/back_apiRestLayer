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

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.controller.ProductTypeController;
import com.sopra.apirestcontroller.domain.service.Impl.ProductTypeServiceImpl;

@SpringBootTest
public class TypeControllerTest {

    @Mock
    private ProductTypeServiceImpl typeService;

    private ProductTypeController typeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(typeService);
        typeController = new ProductTypeController(typeService);
    }

    @Test
    public void testGetAllTypes() {
        List<ProductTypeDto> Types = Arrays.asList(new ProductTypeDto(), new ProductTypeDto());
        Mockito.when(typeService.getAllTypes()).thenReturn(Types);

        List<ProductTypeDto> result = typeController.getAll();

        assertEquals(2, result.size());
        Mockito.verify(typeService, Mockito.times(1)).getAllTypes();
    }

    @Test
    public void testGetType() {
        Long id = 1L;
        ProductTypeDto Type = new ProductTypeDto();
        Mockito.when(typeService.findById(id)).thenReturn(Type);

        ProductTypeDto result = typeController.getType(id).getBody();

        assertNotNull(result);
        Mockito.verify(typeService, Mockito.times(1)).findById(id);
    }

    @Test
    public void testPostCreate() {
        ProductTypeDto type = new ProductTypeDto(1L,"Name");
        Long id = 1L;

        Mockito.when(typeService.create(type)).thenReturn(type);
        ProductTypeDto result = typeController.postCreate(type).getBody();

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Name", result.getName());
        Mockito.verify(typeService, Mockito.times(1)).create(type);
    }

    @Test
    public void testPutUpdate() {
        ProductTypeDto productType = new ProductTypeDto();
        
        Mockito.when(typeService.update(productType)).thenReturn(productType);
        ResponseEntity<ProductTypeDto> result = typeController.putUpdate(productType);

        assertNotNull(result);
        assertEquals(HttpStatus.OK.value(),result.getStatusCode().value());
        Mockito.verify(typeService, Mockito.times(1)).update(productType);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Mockito.when(typeService.deleteById(id)).thenReturn(id);

        Long result = typeController.deleteDelete(id).getBody();

        assertNotNull(result);
        assertEquals(id, result);
        Mockito.verify(typeService, Mockito.times(1)).deleteById(id);
    }

}
