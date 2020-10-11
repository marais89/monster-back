package hello.service;

import hello.dto.UserDto;
import hello.entity.User;
import hello.mappeur.UserMapper;
import hello.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void saveUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public UserDto getUser(String login) {
        return userMapper.mapToDto(usersRepository.findByUsername(login));
    }

}
