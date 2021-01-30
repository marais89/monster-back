package individu.service;

import individu.dto.UserDto;

public interface UsersService {

    public void saveUser(UserDto userDto);

    public UserDto getUser(String login);

    public UserDto update(UserDto userDto);
}
