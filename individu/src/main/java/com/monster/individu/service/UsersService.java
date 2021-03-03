package com.monster.individu.service;

import com.monster.individu.dto.UserDto;

public interface UsersService {

    void saveUser(UserDto userDto);

    UserDto getUser(String username);

    UserDto update(UserDto userDto);
}
