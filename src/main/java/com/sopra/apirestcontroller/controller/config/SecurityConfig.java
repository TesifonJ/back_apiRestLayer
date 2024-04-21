package com.sopra.apirestcontroller.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.sopra.apirestcontroller.common.JwtTokenProvider;
import com.sopra.apirestcontroller.controller.config.filter.JwtTokenValidator;
import com.sopra.apirestcontroller.domain.service.Impl.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtTokenProvider jwtUtils;

    

    public SecurityConfig() {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity oHttpSecurity) throws Exception {
        return oHttpSecurity
        .csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(http -> {
                    // Public
                    http.requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll();
                    http.requestMatchers(PathRequest.toH2Console()).permitAll(); /* Test purpose only to enable H2 console access */
                    
                    //Private
                    http.requestMatchers("/api/products/**").authenticated();
                    http.requestMatchers("/api/types/**").authenticated();

                    //Undefined
                    http.anyRequest().denyAll();
                    


        })
        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
        .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration oAuthenticationConfiguration) throws Exception {
        return oAuthenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailServiceImpl) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceImpl);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
