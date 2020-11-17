package hello.service;

import hello.dto.UserDto;
import hello.entity.User;

public interface UsersService {

    public void saveUser(UserDto userDto);

    public UserDto getUser(String login);

    public UserDto update(UserDto userDto);
}
