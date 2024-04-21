package com.sopra.apirestcontroller.controllerTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import com.sopra.apirestcontroller.common.DTO.AuthLoginRequestDto;
import com.sopra.apirestcontroller.common.DTO.AuthResponseDto;
import com.sopra.apirestcontroller.controller.AuthenticationController;
import com.sopra.apirestcontroller.domain.service.Impl.UserDetailServiceImpl;

@SpringBootTest
public class AuthenticationControllerTest {
    @Mock
    private UserDetailServiceImpl oUserDetailServiceImpl;

    @InjectMocks
    private AuthenticationController oAuthenticationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(oUserDetailServiceImpl);
        oAuthenticationController = new AuthenticationController(oUserDetailServiceImpl);
    }

    @Test
    public void testLoginSuccess() {
        //Arrange
        final String USERNAME= "tesifon";
        final String PASSWORD = "1234";
        final AuthLoginRequestDto USER_REQUEST = new AuthLoginRequestDto(USERNAME, PASSWORD);
        final HttpStatus EXPECTED_STATUS = HttpStatus.OK;
        final String ACCESSTOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InRlc2lmb24iLCJpYXQiOjE1MTYyMzkwMjJ9.JoEQo20RzBgvyk_6FR9yzY2rcuI5HAffgZa3xS3451E";
        final AuthResponseDto EXPECTED_AUTH_RESPONSE = new AuthResponseDto(USERNAME, "User loged succesfully", ACCESSTOKEN, true);
        Mockito.when(oUserDetailServiceImpl.loginUser(USER_REQUEST)).thenReturn(EXPECTED_AUTH_RESPONSE);
        //Act
        ResponseEntity<AuthResponseDto> result = oAuthenticationController.login(USER_REQUEST);
        //Assert
        assertAll(
            () -> assertEquals(EXPECTED_STATUS, result.getStatusCode()),
            () -> assertEquals(EXPECTED_AUTH_RESPONSE, result.getBody())
        );
    }

    @Test
    public void testLoginWrongCredentialError() {
        // Arrange
        final String USERNAME = "tesifon";
        final String PASSWORD = "423";
        final AuthLoginRequestDto USER_REQUEST = new AuthLoginRequestDto(USERNAME, PASSWORD);
        final BadCredentialsException EXPECTED_AUTH_ERROR = new BadCredentialsException(
                "Invalid username or password.");
        Mockito.when(oUserDetailServiceImpl.loginUser(USER_REQUEST)).thenThrow(EXPECTED_AUTH_ERROR);
        // Act
        // Assert
        assertThrows(BadCredentialsException.class, () -> oUserDetailServiceImpl.loginUser(USER_REQUEST));

    }

}
