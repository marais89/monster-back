package individu.service;

import individu.dto.UserDto;
import individu.entity.User;
import individu.mappeur.UserMapper;
import individu.repository.UsersRepository;
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
    public void saveUser(UserDto userDto) {
        usersRepository.save(userMapper.mapToEntity(userDto));
    }

    @Override
    public UserDto getUser(String login) {
        UserDto userDto = userMapper.mapToDto(usersRepository.findByUsername(login));
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = usersRepository.findByUsername(userDto.username);
        if (user != null) {
            user.enabled = userDto.enabled;
            return userMapper.mapToDto(usersRepository.save(user));
        }
        return null;
    }

}
