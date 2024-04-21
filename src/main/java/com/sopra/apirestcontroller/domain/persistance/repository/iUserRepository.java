package com.sopra.apirestcontroller.domain.persistance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.common.DTO.UserDto;

@Repository
public interface iUserRepository {
    UserDto findUserByUsername(String username);
    List<UserDto> getUsers();
    Optional <UserDto> getUserById (Long id);
    UserDto saveUser (UserDto User);
    UserDto updateUser (UserDto User);
    Long deleteUserById(Long id);
}
