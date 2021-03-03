package com.monster.individu.mappeur;

import com.monster.individu.dto.UserDto;
import com.monster.individu.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

    User mapToEntity(UserDto userDto);
}
