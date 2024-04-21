package com.sopra.apirestcontroller.UtilTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sopra.apirestcontroller.common.JwtUtils;
import com.sopra.apirestcontroller.controller.config.filter.JwtTokenValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
public class JwtUtilsTest {
    
    @Mock
    private Authentication authentication;

    @InjectMocks
    private JwtUtils jwtUtils;

    private final String privateKey = "testPrivateKey";
    private final String userGenerator = "testUserGenerator";

    @Test
    public void testCreateToken() {

        Mockito.when(authentication.getPrincipal()).thenReturn("testUser");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Mockito.when(authentication.getAuthorities()).thenReturn(authorities);

        String token = jwtUtils.createToken(authentication);

        DecodedJWT decodedJWT = JWT.decode(token);
        assertEquals("testUser", decodedJWT.getSubject());
        assertEquals(userGenerator, decodedJWT.getIssuer());
        assertEquals("ROLE_USER,ROLE_ADMIN", decodedJWT.getClaim("authorities").asString());
    }

    @Test
    public void testExtractUsername() {
        String token = JWT.create()
                .withIssuer(userGenerator)
                .withSubject("testUser")
                .sign(Algorithm.HMAC256(privateKey));
        DecodedJWT decodedJWT = JWT.decode(token);

        String username = jwtUtils.extractUsername(decodedJWT);

        assertEquals("testUser", username);
    }

    @Test
    public void testGetSpecificClaim() {
        String token = JWT.create()
                .withIssuer(userGenerator)
                .withSubject("testUser")
                .withClaim("customClaim", "testClaim")
                .sign(Algorithm.HMAC256(privateKey));
        DecodedJWT decodedJWT = JWT.decode(token);

        Claim claim = jwtUtils.getSpecificClaim(decodedJWT, "customClaim");

        assertEquals("testClaim", claim.asString());
    }

    @Test
    public void testReturnAllClaims() {
        String token = JWT.create()
                .withIssuer(userGenerator)
                .withSubject("testUser")
                .withClaim("customClaim1", "testClaim1")
                .withClaim("customClaim2", "testClaim2")
                .sign(Algorithm.HMAC256(privateKey));
        DecodedJWT decodedJWT = JWT.decode(token);

        Map<String, Claim> claims = jwtUtils.returnAllClaims(decodedJWT);

        assertEquals("testClaim1", claims.get("customClaim1").asString());
        assertEquals("testClaim2", claims.get("customClaim2").asString());
    }

}
