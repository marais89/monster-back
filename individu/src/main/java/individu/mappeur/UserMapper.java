package individu.mappeur;

import individu.dto.UserDto;
import individu.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

    User mapToEntity(UserDto userDto);
}
