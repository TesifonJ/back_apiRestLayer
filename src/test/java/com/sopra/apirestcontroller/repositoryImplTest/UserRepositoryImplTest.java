package com.sopra.apirestcontroller.repositoryImplTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.sopra.apirestcontroller.common.DTO.UserDto;
import com.sopra.apirestcontroller.controller.mapper.UserDtoMapperImpl;
import com.sopra.apirestcontroller.domain.persistance.DAO.iUserDao;
import com.sopra.apirestcontroller.domain.persistance.entity.UserEntity;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.UserRepositoryImpl;

@SpringBootTest
public class UserRepositoryImplTest {
    @Mock
    private iUserDao oIUserDao;

    @InjectMocks
    private UserRepositoryImpl oUserRepositoryImpl;

    @Mock
    UserDtoMapperImpl oUserDtoMapperImpl;

    private final Long DEFAULT_USER_ID = 1L;
    private final String DEFAULT_USER_USERNAME = "Username";
    private final String DEFAULT_USER_PASSWORD = "Password";
    private final boolean DEFAULT_USER_IS_ENABLED = true;
    private final boolean DEFAULT_USER_ACCOUNT_NOT_EXPIRED = true;
    private final boolean DEFAULT_USER_ACCOUNT_NOT_LOCKED = true;
    private final boolean DEFAULT_USER_CREDENTIAL_NOT_EXPIRED = true;

    final UserEntity DEFAULT_USER_ENTITY = new UserEntity(
            DEFAULT_USER_ID,
            DEFAULT_USER_USERNAME,
            DEFAULT_USER_PASSWORD,
            DEFAULT_USER_IS_ENABLED,
            DEFAULT_USER_ACCOUNT_NOT_EXPIRED,
            DEFAULT_USER_ACCOUNT_NOT_LOCKED,
            DEFAULT_USER_CREDENTIAL_NOT_EXPIRED
            );

    final UserDto DEFAULT_USER_DTO = new UserDto(
            DEFAULT_USER_ID,
            DEFAULT_USER_USERNAME,
            DEFAULT_USER_PASSWORD,
            DEFAULT_USER_IS_ENABLED,
            DEFAULT_USER_ACCOUNT_NOT_EXPIRED,
            DEFAULT_USER_ACCOUNT_NOT_LOCKED,
            DEFAULT_USER_CREDENTIAL_NOT_EXPIRED
            );

            @Test
            public void testFindUserByUsername() {
                // Arrange
                Mockito.when(oIUserDao.findUserByUsername(DEFAULT_USER_USERNAME)).thenReturn(Optional.of(DEFAULT_USER_ENTITY));
        
                // Act
                UserDto result = oUserRepositoryImpl.findUserByUsername(DEFAULT_USER_USERNAME);
        
                // Assert
                assertAll(
                    () -> assertEquals(DEFAULT_USER_ID, result.getId()),
                    () -> assertEquals(DEFAULT_USER_USERNAME, result.getUsername()),
                    () -> assertEquals(DEFAULT_USER_PASSWORD, result.getPassword()),
                    () -> assertEquals(DEFAULT_USER_ACCOUNT_NOT_EXPIRED, result.isAccountNoExpired()),
                    () -> assertEquals(DEFAULT_USER_ACCOUNT_NOT_LOCKED, result.isAccountNoLocked()),
                    () -> assertEquals(DEFAULT_USER_CREDENTIAL_NOT_EXPIRED, result.isCredentialsNoExpired()),
                    () -> assertEquals(DEFAULT_USER_IS_ENABLED, result.isEnabled()),
                    () -> Mockito.verify(oIUserDao, Mockito.times(1)).findUserByUsername(DEFAULT_USER_USERNAME)
                );
            }
}
