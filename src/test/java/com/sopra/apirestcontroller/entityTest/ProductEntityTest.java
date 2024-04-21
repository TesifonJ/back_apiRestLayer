package com.sopra.apirestcontroller.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sopra.apirestcontroller.domain.persistance.entity.ProductEntity;

@SpringBootTest
public class ProductEntityTest {

@Test
public void testNewProductEntity(){
    ProductEntity productEntity = new ProductEntity();
    productEntity.setName("Name");
    productEntity.setDescription("Description");
    productEntity.setPrice(99.99f);
    productEntity.setStock(100);

    assertEquals("Name", productEntity.getName());
    assertEquals("Description", productEntity.getDescription());
    assertEquals(99.99f, productEntity.getPrice());
    assertEquals(100, productEntity.getStock());
}

}
