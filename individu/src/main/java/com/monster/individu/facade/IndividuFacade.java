package com.monster.individu.facade;

import com.monster.history.dto.ActionResult;
import com.monster.history.dto.ActionType;
import com.monster.history.dto.EventsDto;
import com.monster.history.facade.HistoryFacade;
import com.monster.individu.dto.*;
import com.monster.individu.mappeur.UserMapper;
import com.monster.individu.service.AuthoritiesService;
import com.monster.individu.service.IndividusService;
import com.monster.individu.service.UsersService;
import com.monster.individu.service.ValidationKeysService;
import com.monster.notification.dto.MailDto;
import com.monster.notification.service.MailService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("JavaDoc")
@Service
public class IndividuFacade {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IndividusService individusService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private ValidationKeysService validationKeysService;

    @Autowired
    private HistoryFacade historyFacade;

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
     * retrieve individu by username
     *
     * @param username
     * @return IndividuGlobalInfosDto
     */
    public IndividuGlobalInfosDto findByUsername(String username) {

        IndividuDto individuDto = individusService.findByUsername(username);
        EventsDto eventsDto = historyFacade.findLastAuthentication(username, ActionType.AUTHENTICATION.name(), ActionResult.OK.name());
        if (eventsDto != null) {
            individuDto.lastConnexion = eventsDto.datetime;
        }
        return buildIndividuGlobalInfosDto(username, individuDto);
    }

    /**
     * check user by email
     *
     * @param request
     * @return IndividuGlobalInfosDto
     */
    public CheckUserDto checkUserByEmail(UpdateStatusRequest request) {

        EventsDto saveUserEvent = historyFacade.saveHistory(request.requestContext, ActionType.CHECK_USER_FORGET_PWD, ActionResult.INIT);
        IndividuDto individuDto = individusService.findByEmail(request.username);
        if (individuDto != null) {
            if (individuDto.statut.equals("bloque") || individuDto.statut.equals("resilie")) {
                historyFacade.updateHistoryAfterFaild(saveUserEvent);
                return buildFailedCheckUserResponse(CheckUserErrorType.BLOCKED_USER);
            }
            ValidationKeysDto validationKeysDto = validationKeysService.saveValidationKey(individuDto.username);

            historyFacade.saveHistory(request.requestContext, ActionType.GENERATE_AND_SAVE_VALIDATION_KEY, ActionResult.OK);

            try {
                mailService.sendMail(buildForgetPwdMail(individuDto.email, validationKeysDto.secret));
                historyFacade.saveHistory(request.requestContext, ActionType.SEND_FORGET_PWD_EMAIL, ActionResult.OK);
            } catch (Exception ex) {
                logger.error("Error when sending email", ex);
                historyFacade.saveHistory(request.requestContext, ActionType.SEND_FORGET_PWD_EMAIL, ActionResult.ERROR);
                historyFacade.updateHistoryAfterFaild(saveUserEvent);
                return buildFailedCheckUserResponse(CheckUserErrorType.SEND_NOTIF_ERROR);
            }

            historyFacade.updateHistoryAfterSuccess(saveUserEvent);
            return buildSucessCheckUserResponse();
        }
        historyFacade.updateHistoryAfterFaild(saveUserEvent);
        return buildFailedCheckUserResponse(CheckUserErrorType.USER_NOT_FOUND);
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
     * @param individuRequest
     * @return SavingResponseDto
     */
    public SavingResponseDto save(IndividuRequest individuRequest) {
        EventsDto saveUserEvent = null;
        EventsDto generateKeyEvent = null;

        SavingResponseDto code = checkIfUserExiste(individuRequest);
        if (code != null) return code;
        try {
            saveUserEvent = historyFacade.saveHistory(individuRequest.requestContext, ActionType.CREATE_ACCOUNT, ActionResult.INIT);

            individusService.saveNewUser(individuRequest.individu);

            saveUserEvent = historyFacade.updateHistoryAfterSuccess(saveUserEvent);

            ValidationKeysDto validationKeysDto = validationKeysService.saveValidationKey(individuRequest.individu.username);

            generateKeyEvent = historyFacade.saveHistory(individuRequest.requestContext, ActionType.GENERATE_AND_SAVE_VALIDATION_KEY, ActionResult.OK);

            SavingResponseDto codeRetour = sendValidationEmail(individuRequest, validationKeysDto);
            if (codeRetour != null) return codeRetour;

            return new SavingResponseDto(SavingCodeResponse.OK.code);

        } catch (Exception ex) {
            updateHistoryFromState(individuRequest, saveUserEvent, generateKeyEvent);
            return new SavingResponseDto(SavingCodeResponse.TECHNICAL_ERROR.code);
        }
    }

    private SavingResponseDto sendValidationEmail(IndividuRequest individuRequest, ValidationKeysDto validationKeysDto) {
        try {
            mailService.sendMail(buildWelcomeMail(individuRequest.individu.email, validationKeysDto.secret));
            historyFacade.saveHistory(individuRequest.requestContext, ActionType.SEND_EMAIL, ActionResult.OK);
        } catch (Exception ex) {
            historyFacade.saveHistory(individuRequest.requestContext, ActionType.SEND_EMAIL, ActionResult.ERROR);
            return new SavingResponseDto(SavingCodeResponse.MESSAGING_EXCEPTION.code);
        }
        return null;
    }

    /**
     * @param individuRequest
     * @return IndividuDto
     */
    public IndividuDto update(IndividuRequest individuRequest) {

        EventsDto updateUserEvent;
        updateUserEvent = historyFacade.saveHistory(individuRequest.requestContext, ActionType.UPDATE_ACCOUNT, ActionResult.INIT);
        try {
            IndividuDto individuDto = individusService.saveOrUpdate(individuRequest.individu);
            historyFacade.updateHistoryAfterSuccess(updateUserEvent);
            return individuDto;
        } catch (Exception e) {
            historyFacade.updateHistoryAfterFaild(updateUserEvent);
            return null;
        }
    }

    /**
     * Suspend, reactivate or deactivate individu
     *
     * @param username
     * @param status
     * @return List<IndividuDto>
     */
    public List<IndividuDto> updateIndividuStatus(String username, IndividuStatus status, UpdateStatusRequest updateStatusRequest) {

        EventsDto updateUserEvent;
        updateUserEvent = historyFacade.saveHistory(updateStatusRequest.requestContext, convertStatus(status), ActionResult.INIT);

        IndividuDto individu = individusService.findByUsername(username);
        UserDto userDto = updateUser(username, status);
        if (individu != null && userDto != null) {
            individu.statut = status.libelle;
            individusService.saveOrUpdate(individu);
            historyFacade.updateHistoryAfterSuccess(updateUserEvent);
            return individusService.findAll();
        }
        historyFacade.updateHistoryAfterFaild(updateUserEvent);
        return null;
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
     * @param username
     * @param validateKeyRequest
     * @return Boolean
     */
    public Boolean validateKey(String username, ValidateKeyRequest validateKeyRequest) {

        EventsDto updateUserEvent;
        updateUserEvent = historyFacade.saveHistory(validateKeyRequest.requestContext, ActionType.VALIDATE_USER, ActionResult.INIT);
        ValidationKeysDto validationKeysDto = validationKeysService.getValidationKey(validateKeyRequest.key);
        if (verifyKey(username, validationKeysDto)) {
            validationKeysService.closeKey(username);
            updateIndividuStatus(username);
            historyFacade.updateHistoryAfterSuccess(updateUserEvent);
            return Boolean.TRUE;
        }
        historyFacade.updateHistoryAfterFaild(updateUserEvent);
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

    private MailDto buildForgetPwdMail(String to, String secret) {
        //TODO externalize redirect adress
        //TODO externalize e-mail content
        String redirectionLink = "http://localhost:4200/login?key=" + secret;
        MailDto mailDto = new MailDto();
        mailDto.to = to;
        mailDto.subject = "Mot de passe oublié";
        mailDto.content = "<h3> Bonjour </h3> <p>Vous avez oublié votre mot de passe, et vous avez demandé de le changer.</p>" +
                "<p>Cliquez  " +
                "<a href=\'" + redirectionLink + "\'>ICI</a>" +
                " pour pouvoir saisir un nouveau mot de passe, <p/>" +
                "\n Bonne journée.";
        return mailDto;
    }

    private IndividuGlobalInfosDto buildIndividuGlobalInfosDto(String username, IndividuDto individuDto) {
        if (individuDto != null) {
            AuthoritiesDto authoritiesDto = authoritiesService.retrieveUserAutorities(username);
            return new IndividuGlobalInfosDto(individuDto, UserRole.valueOf(authoritiesDto.authority));
        }
        return null;
    }

    private SavingResponseDto checkIfUserExiste(IndividuRequest individuRequest) {

        //TODO à améliorer!
        if (null == individuRequest || null == individuRequest.individu || StringUtils.isEmpty(individuRequest.individu.email) || StringUtils.isEmpty(individuRequest.individu.username)) {
            return new SavingResponseDto(SavingCodeResponse.BAD_REQUEST.code);
        }
        IndividuDto registredUser = individusService.findByUsername(individuRequest.individu.username);
        if (registredUser != null) {
            return new SavingResponseDto(SavingCodeResponse.USER_EXIST.code);
        }
        IndividuDto registredUserWithSameEmail = individusService.findByEmail(individuRequest.individu.email);
        if (registredUserWithSameEmail != null) {
            return new SavingResponseDto(SavingCodeResponse.EMAIL_EXIST.code);
        }
        return null;
    }

    private void updateIndividuStatus(String username) {
        IndividuDto individuDto = individusService.findByUsername(username);
        individuDto.statut = IndividuStatus.ACTIVE.libelle;
        individuDto.niveau = 1;
        individusService.saveOrUpdate(individuDto);
    }

    private UserDto updateUser(String username, IndividuStatus status) {
        UserDto userDto = new UserDto();
        userDto.username = username;
        userDto.enabled = status.equals(IndividuStatus.ACTIVE) || status.equals(IndividuStatus.ATTENTE);
        userDto = usersService.update(userDto);
        return userDto;
    }

    private boolean verifyKey(String username, ValidationKeysDto validationKeysDto) {
        return validationKeysDto != null
                && StringUtils.isNotEmpty(validationKeysDto.secret)
                && validationKeysDto.username.equals(username)
                && !validationKeysDto.used;
    }

    private ActionType convertStatus(IndividuStatus status) {
        switch (status) {
            case ACTIVE:
                return ActionType.REACTIVATE_USER;
            case BLOQUE:
                return ActionType.SUSPEND_USER;
            case RESILIE:
                return ActionType.DEACTIVATE_USER;
            default:
                return ActionType.UNKNOWN;
        }
    }

    private void updateHistoryFromState(IndividuRequest individuRequest, EventsDto saveUserEvent, EventsDto generateKeyEvent) {
        if (saveUserEvent == null) {
            historyFacade.saveHistory(individuRequest.requestContext, ActionType.CREATE_ACCOUNT, ActionResult.ERROR);
        } else if (saveUserEvent.actionResult.equals(ActionResult.INIT.name())) {
            historyFacade.updateHistoryAfterFaild(saveUserEvent);
        } else if (generateKeyEvent == null) {
            historyFacade.saveHistory(individuRequest.requestContext, ActionType.GENERATE_AND_SAVE_VALIDATION_KEY, ActionResult.ERROR);
        }
    }

    private CheckUserDto buildFailedCheckUserResponse(CheckUserErrorType error) {
        CheckUserDto checkUser = new CheckUserDto();
        checkUser.isValidUser = Boolean.FALSE;
        checkUser.errorType = error;
        return checkUser;
    }

    private CheckUserDto buildSucessCheckUserResponse() {
        CheckUserDto checkUser = new CheckUserDto();
        checkUser.isValidUser = Boolean.TRUE;
        return checkUser;
    }
}
