package hello.facade;

import hello.dto.IndividuDto;
import hello.dto.IndividuStatus;
import hello.dto.UserDto;
import hello.mappeur.UserMapper;
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

    @Autowired
    UserMapper userMapper;

    public List<IndividuDto> findAll() {
        return individusService.findAll();
    }

    public IndividuDto getByEmail(String email) {
        return individusService.findByEmail(email);
    }

    public List<IndividuDto> getByNumeroTel(String numero) {
        return individusService.findByNumeroTel(numero);
    }

    public void save(IndividuDto individuDto) {
        individusService.saveNewUser(individuDto);
    }

    public IndividuDto update(IndividuDto individuDto) {
        return individusService.saveOrUpdate(individuDto);
    }

    public List<IndividuDto> updateIndividuStatus(String login, IndividuStatus status) {
        IndividuDto individuToDisable = individusService.findByEmail(login);
        UserDto userDto = new UserDto();
        userDto.username = login;
        userDto.enabled = status.equals(IndividuStatus.ACTIVE) || status.equals(IndividuStatus.ATTENTE);
        userDto = usersService.update(userDto);
        if (individuToDisable != null && userDto != null) {
            individuToDisable.statut = status.libelle;
            individusService.saveOrUpdate(individuToDisable);
            return individusService.findAll();
        }
        return null;
    }

    public void delete(Long id) {
        individusService.delete(id);
    }

    public UserDto findUser(String login) {
        return usersService.getUser(login);
    }
}
