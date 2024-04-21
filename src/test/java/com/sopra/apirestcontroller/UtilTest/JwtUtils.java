package com.sopra.apirestcontroller.UtilTest;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.sopra.apirestcontroller.common.JwtTokenProvider;
import com.sopra.apirestcontroller.domain.service.Impl.UserDetailServiceImpl;

@SpringBootTest
public class JwtUtils {
    @Mock
    private Authentication authentication;

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    UserDetailServiceImpl oDetailServiceImpl;

    @Test
    public void testCreateToken() {
        String username = "UserName";
        String password = "Password";
        Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        
        Mockito.when(oDetailServiceImpl.authenticate(username, username).getPrincipal()).thenReturn(username);
        Mockito.when(oDetailServiceImpl.authenticate(username, username).get).thenReturn(authorities);
        
        String jwtToken = jwtTokenProvider.createToken(authentication);

        assertTrue(jwtToken.startsWith("eyJhbGciOiJIUzI1NiJ9")); // This checks if the token starts with the standard JWT header for HMAC256
        assertTrue(jwtToken.split("\\.").length == 3); // This checks if the token has three parts (header, payload, signature)
    }
}
