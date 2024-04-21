package com.sopra.apirestcontroller.domain.service.Impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sopra.apirestcontroller.common.JwtTokenProvider;
import com.sopra.apirestcontroller.common.DTO.AuthLoginRequestDto;
import com.sopra.apirestcontroller.common.DTO.AuthResponseDto;
import com.sopra.apirestcontroller.common.DTO.UserDto;
import com.sopra.apirestcontroller.domain.persistance.repository.Impl.UserRepositoryImpl;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private JwtTokenProvider jwtUtils;
    private PasswordEncoder oPasswordEncoder;
    private UserRepositoryImpl oUserRepositoryImpl;

    @Autowired
    public UserDetailServiceImpl(UserRepositoryImpl oUserRepositoryImpl, JwtTokenProvider jwtUtils, PasswordEncoder oPasswordEncoder) {
        this.oUserRepositoryImpl = oUserRepositoryImpl;
        this.jwtUtils = jwtUtils;
        this.oPasswordEncoder = oPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto oUserDto = oUserRepositoryImpl.findUserByUsername(username);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        oUserDto.getRoles()
                .forEach(role -> authorityList
                        .add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleUserEnum().name()))));

        oUserDto.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

                return new User(oUserDto.getUsername(), oUserDto.getPassword(), oUserDto.isEnabled(), oUserDto.isAccountNoExpired(), oUserDto.isCredentialsNoExpired(), oUserDto.isAccountNoLocked(), authorityList);
    }

    public AuthResponseDto loginUser(AuthLoginRequestDto authLoginRequestDto) throws BadCredentialsException{
        String username = authLoginRequestDto.username();
        String password = authLoginRequestDto.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDto authResponse = new AuthResponseDto(username, "User loged succesfully", accessToken, true);

        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails oUserDetails = this.loadUserByUsername(username);
        if (oUserDetails == null) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        if (!oPasswordEncoder.matches(password, oUserDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }

        return new UsernamePasswordAuthenticationToken(username, oUserDetails.getPassword(),
                oUserDetails.getAuthorities());
    }

}