package com.sopra.apirestcontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopra.apirestcontroller.common.DTO.AuthLoginRequestDto;
import com.sopra.apirestcontroller.common.DTO.AuthResponseDto;
import com.sopra.apirestcontroller.domain.service.Impl.UserDetailServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthenticationController {

    
    private UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    public AuthenticationController(UserDetailServiceImpl userDetailServiceImpl) {
        this.userDetailServiceImpl = userDetailServiceImpl;
    }



    @PostMapping("/log-in")
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthLoginRequestDto userRequest) {
        return new ResponseEntity<>(userDetailServiceImpl.loginUser(userRequest), HttpStatus.OK);
    }
}
