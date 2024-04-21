package com.sopra.apirestcontroller.entityTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sopra.apirestcontroller.domain.persistance.entity.ProductTypeEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TypeEntityTest {

    @Test
    public void testNewTypeEntity() {
        ProductTypeEntity TypeEntity = new ProductTypeEntity();
        TypeEntity.setId(1L);
        TypeEntity.setName("Test Type 1");

        assertEquals(1L, TypeEntity.getId());
        assertEquals("Test Type 1", TypeEntity.getName());

    }
}
