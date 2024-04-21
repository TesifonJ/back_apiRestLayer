package com.sopra.apirestcontroller.controller.mapper;

import java.util.List;

import com.sopra.apirestcontroller.common.DTO.UserDto;
import com.sopra.apirestcontroller.domain.persistance.entity.UserEntity;

public interface iUserEntityDtoMapper {
    UserDto toUserDTO(UserEntity userEntity);

    List<UserDto> toUserDTOList(List<UserEntity> productEntity);

    UserEntity toUserEntity(UserDto entityDTO);
}
