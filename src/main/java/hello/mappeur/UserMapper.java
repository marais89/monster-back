package hello.mappeur;

import hello.dto.UserDto;
import hello.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);
}
