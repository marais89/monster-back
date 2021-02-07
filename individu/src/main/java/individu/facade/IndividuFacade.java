package individu.facade;

import individu.dto.*;
import individu.mappeur.UserMapper;
import individu.service.AuthoritiesService;
import individu.service.IndividusService;
import individu.service.UsersService;
import individu.service.ValidationKeysService;
import notification.dto.MailDto;
import notification.service.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Collections;
import java.util.List;

@Service
public class IndividuFacade {

    @Autowired
    private IndividusService individusService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private ValidationKeysService validationKeysService;

    @Autowired
    UsersService usersService;

    @Autowired
    UserMapper userMapper;

    /**
     * retrieve all individus
     *
     * @return List<IndividuDto>
     */
    public List<IndividuDto> findAll() {

        return individusService.findAll();
    }

    /**
     * retrieve individu by e-mail
     *
     * @param email
     * @return IndividuGlobalInfosDto
     */
    public IndividuGlobalInfosDto findByEmail(String email) {

        IndividuDto individuDto = individusService.findByEmail(email);
        return buildIndividuGlobalInfosDto(email, individuDto);
    }

    /**
     * retrieve individu by phone number
     *
     * @param numero
     * @return List<IndividuDto>
     */
    public List<IndividuDto> findByNumeroTel(String numero) {

        return individusService.findByNumeroTel(numero);
    }

    /**
     * save individu
     *
     * @param individuDto
     * @return SavingResponseDto
     */
    public SavingResponseDto save(IndividuDto individuDto) {

        SavingResponseDto code = checkIfUserExiste(individuDto);
        if (code != null) return code;
        try {
            individusService.saveNewUser(individuDto);

            ValidationKeysDto validationKeysDto = validationKeysService.saveValidationKey(individuDto.email);

            mailService.sendMail(buildWelcomeMail(individuDto.email, validationKeysDto.secret));

            return new SavingResponseDto(SavingCodeResponse.OK.code);

        } catch (MessagingException mex) {
            return new SavingResponseDto(SavingCodeResponse.MESSAGING_EXCEPTION.code);
        } catch (Exception ex) {
            return new SavingResponseDto(SavingCodeResponse.TECHNICAL_ERROR.code);
        }
    }

    /**
     * update individu
     *
     * @param individuDto
     * @return IndividuDto
     */
    public IndividuDto update(IndividuDto individuDto) {

        return individusService.saveOrUpdate(individuDto);
    }

    /**
     * Suspend, reactivate or deactivate individu
     *
     * @param login
     * @param status
     * @return List<IndividuDto>
     */
    public List<IndividuDto> updateIndividuStatus(String login, IndividuStatus status) {

        IndividuDto individu = individusService.findByEmail(login);
        UserDto userDto = updateUser(login, status);
        if (individu != null && userDto != null) {
            individu.statut = status.libelle;
            individusService.saveOrUpdate(individu);
            return individusService.findAll();
        }
        return Collections.emptyList();
    }

    /**
     * delete individu
     *
     * @param id
     */
    public void delete(Long id) {
        //TODO return list of individus
        individusService.delete(id);
    }

    /**
     * find user
     *
     * @param login
     * @return UserDto
     */
    public UserDto findUser(String login) {

        return usersService.getUser(login);
    }

    /**
     * check if validation key is valid
     *
     * @param login
     * @param key
     * @return Boolean
     */
    public Boolean validateKey(String login, String key) {
        ValidationKeysDto validationKeysDto = validationKeysService.getValidationKey(key);
        if (verifyKey(login, validationKeysDto)) {
            validationKeysService.closeKey(login);
            updateIndividuStatus(login);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private MailDto buildWelcomeMail(String to, String secret) {
        //TODO externalize redirect adress
        //TODO externalize e-mail content
        String redirectionLink = "http://localhost:4200?key=" + secret;
        MailDto mailDto = new MailDto();
        mailDto.to = to;
        mailDto.subject = "Bienvenue chez MONSTER";
        mailDto.content = "<h3> Bonjour </h3> <p>Toute l'équipe  est heureuse de vous compter parmi ses clients.</p>" +
                "<p>Cliquez  " +
                "<a href=\'" + redirectionLink + "\'>ICI</a>" +
                " pour activer votre compte, <p/>" +
                "\n Bonne journée.";
        return mailDto;
    }

    private IndividuGlobalInfosDto buildIndividuGlobalInfosDto(String email, IndividuDto individuDto) {
        if (individuDto != null) {
            AuthoritiesDto authoritiesDto = authoritiesService.retrieveUserAutorities(email);
            return new IndividuGlobalInfosDto(individuDto, UserRole.valueOf(authoritiesDto.authority));
        }
        return null;
    }

    private SavingResponseDto checkIfUserExiste(IndividuDto individuDto) {
        if (StringUtils.isEmpty(individuDto.email)) {
            return new SavingResponseDto(SavingCodeResponse.BAD_REQUEST.code);
        }
        IndividuDto registredUser = individusService.findByEmail(individuDto.email);
        if (registredUser != null) {
            return new SavingResponseDto(SavingCodeResponse.USER_EXISTE.code);
        }
        return null;
    }

    private void updateIndividuStatus(String login) {
        IndividuDto individuDto = individusService.findByEmail(login);
        individuDto.statut = IndividuStatus.ACTIVE.libelle;
        individuDto.niveau = 1;
        individusService.saveOrUpdate(individuDto);
    }

    private UserDto updateUser(String login, IndividuStatus status) {
        UserDto userDto = new UserDto();
        userDto.username = login;
        userDto.enabled = status.equals(IndividuStatus.ACTIVE) || status.equals(IndividuStatus.ATTENTE);
        userDto = usersService.update(userDto);
        return userDto;
    }

    private boolean verifyKey(String login, ValidationKeysDto validationKeysDto) {
        return validationKeysDto != null
                && StringUtils.isNotEmpty(validationKeysDto.secret)
                && validationKeysDto.username.equals(login)
                && !validationKeysDto.used;
    }
}
