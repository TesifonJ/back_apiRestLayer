package com.sopra.apirestcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import com.sopra.apirestcontroller.common.JwtUtils;
import com.sopra.apirestcontroller.controller.config.SecurityConfig;
import com.sopra.apirestcontroller.domain.service.Impl.UserDetailServiceImpl;


@SpringBootTest
public class SecurityConfigTest {
 
    @InjectMocks
    private SecurityConfig securityConfig;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private HttpSecurity httpSecurity;

    @Mock
    private DefaultSecurityFilterChain securityFilterChain;

    @Mock
    private UserDetailServiceImpl userDetailServiceImpl;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testSecurityFilterChain() throws Exception {
        SecurityConfig securityConfig = new SecurityConfig();
        HttpSecurity httpSecurityMock = mock(HttpSecurity.class);

        SecurityFilterChain result = securityConfig.securityFilterChain(Mockito.any(HttpSecurity.class));

        Mockito.when(httpSecurity.csrf(Mockito.any())).thenReturn(httpSecurityMock);
        Mockito.when(httpSecurity.httpBasic(Mockito.any())).thenReturn(httpSecurityMock);
        Mockito.when(httpSecurity.sessionManagement(Mockito.any())).thenReturn(httpSecurityMock);
        Mockito.when(httpSecurity.authorizeHttpRequests(Mockito.any())).thenReturn(httpSecurityMock);
        Mockito.when(httpSecurity.headers(Mockito.any())).thenReturn(httpSecurityMock);
        Mockito.when(httpSecurity.addFilterBefore(Mockito.any(), Mockito.any())).thenReturn(httpSecurityMock);
        Mockito.when(httpSecurity.build()).thenReturn(securityFilterChain);


        Mockito.verify(httpSecurityMock, Mockito.times(1)).csrf(Mockito.any());
        Mockito.verify(httpSecurityMock, Mockito.times(1)).httpBasic(Mockito.any());
        Mockito.verify(httpSecurityMock, Mockito.times(1)).sessionManagement(Mockito.any());
        Mockito.verify(httpSecurityMock, Mockito.times(1)).authorizeHttpRequests(Mockito.any());
        Mockito.verify(httpSecurityMock, Mockito.times(1)).headers(Mockito.any());
        Mockito.verify(httpSecurityMock, Mockito.times(1)).addFilterBefore(Mockito.any(), Mockito.any());
        Mockito.verify(httpSecurityMock, Mockito.times(1)).build();

        assertEquals(securityFilterChain, result);
    }

    @Test
    void testAuthenticationManager() throws Exception {
        securityConfig.authenticationManager(authenticationConfiguration);
        Mockito.verify(authenticationConfiguration, Mockito.times(1)).getAuthenticationManager();
    }

    @Test
    void testAuthenticationProvider() {
        securityConfig.authenticationProvider(userDetailServiceImpl);
        Mockito.verify(userDetailServiceImpl, Mockito.times(1)).loadUserByUsername(Mockito.anyString());
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();
        assert(encoder != null);
        
    }
}
