package hello.facade;

import hello.dto.IndividuDto;
import hello.dto.UserDto;
import hello.service.IndividusService;
import hello.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividuFacade {

    @Autowired
    private IndividusService individusService;

    @Autowired
    UsersService usersService;

    public List<IndividuDto> findAll() {
        return individusService.findAll();
    }

    public List<IndividuDto> getByEmail(String email) {
        return individusService.findByEmail(email);
    }

    public List<IndividuDto> getByNumeroTel(String numero) {
        return individusService.findByNumeroTel(numero);
    }

    public void save(IndividuDto individuDto) {
        individusService.save(individuDto);
    }

    public void delete(Long id) {
        individusService.delete(id);
    }

    public UserDto findUser(String login) {
        return usersService.getUser(login);
    }
}
