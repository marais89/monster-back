package hello.facade;

import hello.dto.*;
import hello.mappeur.UserMapper;
import hello.service.AuthoritiesService;
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
    private AuthoritiesService authoritiesService;

    @Autowired
    UsersService usersService;

    @Autowired
    UserMapper userMapper;

    public List<IndividuDto> findAll() {
        return individusService.findAll();
    }

    public IndividuGlobalInfosDto getByEmail(String email) {
        IndividuDto individuDto = individusService.findByEmail(email);
        if (individuDto != null) {
            AuthoritiesDto authoritiesDto = authoritiesService.retrieveUserAutorities(email);
            return new IndividuGlobalInfosDto(individuDto, UserRole.valueOf(authoritiesDto.authority));
        }
        return null;
    }

    public List<IndividuDto> getByNumeroTel(String numero) {
        return individusService.findByNumeroTel(numero);
    }

    public SavingResponseDto save(IndividuDto individuDto) {
        if (individuDto.email == null || individuDto.email == "") {
            return new SavingResponseDto(SavingCodeResponse.BAD_REQUEST.code);
        }
        IndividuDto registredUser = individusService.findByEmail(individuDto.email);
        if (registredUser != null) {
            return new SavingResponseDto(SavingCodeResponse.USER_EXISTE.code);
        }
        try {
            individusService.saveNewUser(individuDto);
            return new SavingResponseDto(SavingCodeResponse.OK.code);
        } catch (Exception ex) {
            return new SavingResponseDto(SavingCodeResponse.TECHNICAL_ERROR.code);
        }
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
