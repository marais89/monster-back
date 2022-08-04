package com.monster.individu.service;

import com.monster.individu.dto.UserDto;
import com.monster.individu.entity.User;
import com.monster.individu.mappeur.UserMapper;
import com.monster.individu.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void saveUser(UserDto userDto) {
        usersRepository.save(userMapper.mapToEntity(userDto));
    }

    @Override
    public UserDto getUser(String username) {
        UserDto userDto = userMapper.mapToDto(usersRepository.findByUsername(username));
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = usersRepository.findByUsername(userDto.username);
        if (user != null) {
            user.setEnabled(userDto.enabled);
            return userMapper.mapToDto(usersRepository.save(user));
        }
        return null;
    }

}
