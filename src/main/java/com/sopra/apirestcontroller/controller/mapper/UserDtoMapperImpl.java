package com.sopra.apirestcontroller.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.sopra.apirestcontroller.common.DTO.UserDto;
import com.sopra.apirestcontroller.domain.persistance.entity.UserEntity;

public class UserDtoMapperImpl implements iUserEntityDtoMapper {

    @Override
    public UserDto toUserDTO(UserEntity userEntity) {
        UserDto userDto = new UserDto();

        if (userEntity.getId() != null) {
            userDto.setId(userEntity.getId());
        }
        userDto.setUsername(userEntity.getUsername());
        userDto.setPassword(userEntity.getPassword());
        userDto.setRoles(userEntity.getRoles());
        userDto.setEnabled(userEntity.isEnabled());
        userDto.setAccountNoExpired(userEntity.isAccountNoExpired());
        userDto.setAccountNoLocked(userEntity.isAccountNoLocked());
        userDto.setCredentialsNoExpired(userEntity.isCredentialsNoExpired());

        return userDto;
    }

    @Override
    public List<UserDto> toUserDTOList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(userEntity -> toUserDTO(userEntity))
                .collect(Collectors.toList());
    }

    @Override
    public UserEntity toUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        if (userDto.getId() != null) {
            userEntity.setId(userDto.getId());
        }
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRoles(userDto.getRoles());
        userEntity.setEnabled(userDto.isEnabled());
        userEntity.setAccountNoExpired(userDto.isAccountNoExpired());
        userEntity.setAccountNoLocked(userDto.isAccountNoLocked());
        userEntity.setCredentialsNoExpired(userDto.isCredentialsNoExpired());

        return userEntity;
    }

}
