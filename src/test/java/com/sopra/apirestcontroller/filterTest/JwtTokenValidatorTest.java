package com.sopra.apirestcontroller.filterTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sopra.apirestcontroller.common.JwtUtils;
import com.sopra.apirestcontroller.controller.config.filter.JwtTokenValidator;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
public class JwtTokenValidatorTest {

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private JwtTokenValidator jwtTokenValidator;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Test
    public void testDoFilterInternal() throws ServletException, IOException {
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer testToken");

        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        Mockito.when(jwtUtils.validateToken("testToken")).thenReturn(decodedJWT);
        Mockito.when(jwtUtils.extractUsername(decodedJWT)).thenReturn("testUser");
        Claim authoritiesClaim = mock(Claim.class);
        Mockito.when(authoritiesClaim.asString()).thenReturn("ROLE_USER");
        Mockito.when(jwtUtils.getSpecificClaim(decodedJWT, "authorities")).thenReturn(authoritiesClaim);

        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        jwtTokenValidator.doFilter(request, response, filterChain);

        verify(jwtUtils).validateToken("testToken");
        verify(jwtUtils).extractUsername(decodedJWT);
        verify(jwtUtils).getSpecificClaim(decodedJWT, "authorities");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternalWithoutToken() throws Exception {
        Mockito.when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(null);

        jwtTokenValidator.doFilter(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assertNull(authentication);
        Mockito.verify(filterChain).doFilter(request, response);
    }

}
