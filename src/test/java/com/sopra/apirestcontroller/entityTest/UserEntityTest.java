package com.sopra.apirestcontroller.entityTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sopra.apirestcontroller.domain.persistance.entity.UserEntity;

@SpringBootTest
public class UserEntityTest {
    @Test
    public void testNewUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("username");
        userEntity.setPassword("password");
        userEntity.setAccountNoExpired(true);
        userEntity.setAccountNoLocked(true);
        userEntity.setCredentialsNoExpired(true);
        userEntity.setEnabled(true);

        assertAll(
            () -> assertEquals(1L, userEntity.getId()),
            () -> assertEquals("username", userEntity.getUsername()),
            () -> assertEquals("password", userEntity.getPassword()),
            () -> assertEquals(true, userEntity.isAccountNoExpired()),
            () -> assertEquals(true, userEntity.isAccountNoLocked()),
            () -> assertEquals(true, userEntity.isCredentialsNoExpired()),
            () -> assertEquals(true, userEntity.isEnabled())
        );
    }
}
