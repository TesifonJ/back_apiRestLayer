package com.sopra.apirestcontroller.serviceTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sopra.apirestcontroller.common.JwtUtils;
import com.sopra.apirestcontroller.common.DTO.AuthLoginRequestDto;
import com.sopra.apirestcontroller.common.DTO.UserDto;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.UserRepositoryImpl;
import com.sopra.apirestcontroller.domain.service.Impl.UserDetailServiceImpl;

@SpringBootTest
public class UserDetailServiceTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private UserRepositoryImpl userRepositoryImpl;

    @Mock
    private JwtUtils jwtTokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testLoadUserByUsername() {
        String username = "testUser";
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        Mockito.when(userRepositoryImpl.findUserByUsername(username)).thenReturn(userDto);

        UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);

        Mockito.verify(userRepositoryImpl, Mockito.times(1)).findUserByUsername(username);
        assert userDetails.getUsername().equals(username);
    }

    @Test
    public void testLoginUser() {
        String username = "testUser";
        String password = "testPassword";
        AuthLoginRequestDto authLoginRequestDto = new AuthLoginRequestDto(username, password);

        Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        userDetailServiceImpl.loginUser(authLoginRequestDto);

        Mockito.verify(passwordEncoder, Mockito.times(1)).matches(Mockito.anyString(), Mockito.anyString());
    }
}
