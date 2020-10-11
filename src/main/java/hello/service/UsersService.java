package hello.service;

import hello.dto.UserDto;
import hello.entity.User;

public interface UsersService {

    public void saveUser(User user);

    public UserDto getUser(String login);
}
