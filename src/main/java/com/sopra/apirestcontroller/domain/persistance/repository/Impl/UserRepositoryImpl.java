package com.sopra.apirestcontroller.domain.persistance.repository.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.sopra.apirestcontroller.common.DTO.UserDto;
import com.sopra.apirestcontroller.controller.mapper.UserDtoMapperImpl;
import com.sopra.apirestcontroller.domain.persistance.DAO.iUserDao;
import com.sopra.apirestcontroller.domain.persistance.entity.UserEntity;
import com.sopra.apirestcontroller.domain.persistance.repository.iUserRepository;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class UserRepositoryImpl implements iUserRepository {

    iUserDao oIUserDao;

    @Autowired
    public UserRepositoryImpl(iUserDao oIUserDao) {
        this.oIUserDao = oIUserDao;
    }

    public UserRepositoryImpl() {
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> dataBaseUsers = oIUserDao.findAll();
        List<UserDto> dtoUsers = new ArrayList<UserDto>();
        UserDtoMapperImpl oDtoMapperImpl = new UserDtoMapperImpl();

        for (UserEntity UserEntity : dataBaseUsers) {
            dtoUsers.add(oDtoMapperImpl.toUserDTO(UserEntity));
        }
        return dtoUsers;
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        UserDtoMapperImpl oDtoMapperImpl = new UserDtoMapperImpl();

        UserEntity dataBaseUser = oIUserDao.findById(id)
                // Change error
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found"));
        return Optional.of(oDtoMapperImpl.toUserDTO(dataBaseUser));
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserDtoMapperImpl oDtoMapperImpl = new UserDtoMapperImpl();
        UserEntity newUserEntity = oDtoMapperImpl.toUserEntity(userDto);

        newUserEntity.setId(null);

        oIUserDao.saveAndFlush(newUserEntity);

        return getUserById(newUserEntity.getId()).get();
    }

    @Override
    public UserDto updateUser(UserDto updatedUser) {
        UserDto currentDBUserToUpdate = getUserById(updatedUser.getId()).get();
        UserDtoMapperImpl oDtoMapperImpl = new UserDtoMapperImpl();

        currentDBUserToUpdate.setId(updatedUser.getId());
        currentDBUserToUpdate.setUsername(updatedUser.getUsername());
        currentDBUserToUpdate.setPassword(updatedUser.getPassword());
        currentDBUserToUpdate.setRoles(updatedUser.getRoles());
        currentDBUserToUpdate.setEnabled(updatedUser.isEnabled());
        currentDBUserToUpdate.setAccountNoExpired(updatedUser.isAccountNoExpired());
        currentDBUserToUpdate.setAccountNoLocked(updatedUser.isAccountNoLocked());
        currentDBUserToUpdate.setCredentialsNoExpired(updatedUser.isAccountNoLocked());

        oIUserDao.saveAndFlush(oDtoMapperImpl.toUserEntity(currentDBUserToUpdate));

        return currentDBUserToUpdate;
    }

    @Override
    public Long deleteUserById(Long id) {
        UserEntity userEntity = oIUserDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User " + id + " not found"));
        oIUserDao.deleteById(userEntity.getId());
        return userEntity.getId();
    }

    @Override
    public UserDto findUserByUsername(String username) {
        UserDtoMapperImpl userDtoMapperImpl = new UserDtoMapperImpl();
        UserEntity userEntity = oIUserDao.findUserByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username));

       return userDtoMapperImpl.toUserDTO(userEntity);
    }

}
