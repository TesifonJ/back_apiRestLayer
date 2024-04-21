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

import com.sopra.apirestcontroller.common.DTO.ProductTypeDto;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.ProductTypeRepositoryImpl;
import com.sopra.apirestcontroller.domain.service.Impl.ProductTypeServiceImpl;

@SpringBootTest
public class TypeServiceTest {

    @Mock
    private ProductTypeRepositoryImpl TypeRepository;

    private ProductTypeServiceImpl ProductTypeServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(TypeRepository);
        ProductTypeServiceImpl = new ProductTypeServiceImpl(TypeRepository);
    }

    @Test
    public void testGetAllTypes() {
        ProductTypeDto oProductTypeDto = new ProductTypeDto(1L, "Name");
        List<ProductTypeDto> Types = Arrays.asList(oProductTypeDto, oProductTypeDto);
        
        Mockito.when(TypeRepository.getProductTypes()).thenReturn(Types);

        List<ProductTypeDto> result = ProductTypeServiceImpl.getAllTypes();

        assertEquals(2, result.size());
        Mockito.verify(TypeRepository, Mockito.times(1)).getProductTypes();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        ProductTypeDto oTypeDto = new ProductTypeDto(1L, "Name");

        Mockito.when(TypeRepository.getProductType(id)).thenReturn(Optional.of(oTypeDto));
        ProductTypeDto result = ProductTypeServiceImpl.findById(id);

        assertNotNull(result);
        Mockito.verify(TypeRepository, Mockito.times(1)).getProductType(id);
    }

    @Test
    public void testFindByIdCorrectAttributes() {
        Long id = 1L;
        ProductTypeDto oTypeDto = new ProductTypeDto(1L, "Name");
        Mockito.when(TypeRepository.getProductType(id)).thenReturn(Optional.of(oTypeDto));

        ProductTypeDto result = ProductTypeServiceImpl.findById(id);

        assertNotNull(result);
        assertEquals(oTypeDto.getId(), result.getId());
        assertEquals(oTypeDto.getName(), result.getName());
    }

    @Test
    public void testCreateCorrectAttributes() {
        Long id = 1L;
        ProductTypeDto oTypeDto = new ProductTypeDto(1L, "Name");
        Mockito.when(TypeRepository.saveProductType(oTypeDto)).thenReturn(oTypeDto);

        ProductTypeDto result = ProductTypeServiceImpl.create(oTypeDto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        Mockito.verify(TypeRepository, Mockito.times(1)).saveProductType(oTypeDto);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        ProductTypeDto bodyTypeDto = new ProductTypeDto(1L, "newName");

        Mockito.when(TypeRepository.saveProductType(bodyTypeDto)).thenReturn(bodyTypeDto);
        Mockito.when(TypeRepository.getProductType(id)).thenReturn(Optional.of(bodyTypeDto));

        ProductTypeDto result = ProductTypeServiceImpl.update(bodyTypeDto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        Mockito.verify(TypeRepository, Mockito.times(1)).saveProductType(bodyTypeDto);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        Mockito.when(TypeRepository.deleteProductTypeById(id)).thenReturn(id);

        Long result = ProductTypeServiceImpl.deleteById(id);

        assertEquals(id, result);
        Mockito.verify(TypeRepository, Mockito.times(1)).deleteProductTypeById(id);
    }
}
