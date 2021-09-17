package com.monster.authent.facade;

import com.monster.authent.dto.*;
import com.monster.history.dto.ActionResult;
import com.monster.history.dto.ActionType;
import com.monster.history.dto.EventsDto;
import com.monster.history.dto.RequestContext;
import com.monster.history.facade.HistoryFacade;
import com.monster.individu.dto.IndividuStatus;
import com.monster.individu.dto.UpdateStatusRequest;
import com.monster.individu.dto.UserDto;
import com.monster.individu.dto.ValidationKeysDto;
import com.monster.individu.facade.IndividuFacade;
import com.monster.individu.service.UsersService;
import com.monster.individu.service.ValidationKeysService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Service
public class AuthentFacade {

    @Autowired
    HistoryFacade historyFacade;

    @Autowired
    UsersService usersService;

    @Autowired
    private ValidationKeysService validationKeysService;

    @Autowired
    IndividuFacade individuFacade;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * verify username and pwd
     *
     * @param loginRequest
     * @return UserDto
     */
    public LoginResponse login(LoginRequest loginRequest) throws GeneralSecurityException {

        String[] infos = retrieveCredentialsFromRequest(loginRequest);
        EventsDto event = null;
        LoginResponse loginResponse = new LoginResponse();
        UserDto user = null;
        if (infos.length == 2 && StringUtils.isNotEmpty(infos[0]) && StringUtils.isNotEmpty(infos[1])) {
            String login = infos[0].strip();
            loginRequest.requestContext.username = login;
            event = historyFacade.saveHistory(loginRequest.requestContext, ActionType.AUTHENTICATION, ActionResult.INIT);

            user = usersService.getUser(login);
            if (user != null && user.enabled) {
                if (isMatches(infos[1], user.password)) {
                    historyFacade.updateHistoryAfterSuccess(event);
                    return buildResponseWithJWTToken(loginResponse, loginRequest, user);
                }

                if (historyFacade.findFaieldConnetionNumberToday(infos[0]) > 3) {
                    individuFacade.updateIndividuStatus(user.username, IndividuStatus.BLOQUE, buildSystemUpdateStatus(user.username));
                }
            }
        }
        updateEvent(loginRequest, event);
        loginResponse.failedTentativeCount = historyFacade.findFaieldConnetionNumberToday(infos[0]);
        loginResponse.isUserActive = user != null ? user.enabled : null;
        loginResponse.isValidCredentials = false;
        return loginResponse;
    }

    public UserDto updatePwd(UpdatePwdDto updatePwdDto) {
        EventsDto event = historyFacade.saveHistory(updatePwdDto.requestContext, ActionType.UPDATEPWD, ActionResult.INIT);

        UserDto user = usersService.getUser(updatePwdDto.requestContext.username);
        if (user != null) {
            if (isMatches(updatePwdDto.oldPwd, user.password)) {
                user.password = updatePwdDto.newPwd;
                usersService.saveUser(user);
                historyFacade.updateHistoryAfterSuccess(event);
                return user;
            }
        }
        historyFacade.updateHistoryAfterFaild(event);
        return null;
    }

    public UpdatePwdWithKeyResponse updatePwdWithKey(UpdatePwdWithKeyDto updatePwdWithKey) {

        ValidationKeysDto validationKeysDto = validationKeysService.getValidationKey(updatePwdWithKey.key);
        if (verifyKey(validationKeysDto)) {
            updatePwdWithKey.requestContext.username = validationKeysDto.username;
            EventsDto event = historyFacade.saveHistory(updatePwdWithKey.requestContext, ActionType.UPDATEPWD_WITH_KEY, ActionResult.INIT);
            UserDto user = usersService.getUser(validationKeysDto.username);
            if (user != null) {
                user.password = updatePwdWithKey.newPassword;
                usersService.saveUser(user);
                historyFacade.updateHistoryAfterSuccess(event);
                validationKeysService.closeKey(validationKeysDto.username);
                return buildUpdatePwdWithKeyResponse(ResponseType.OK);
            }
            historyFacade.updateHistoryAfterFaild(event);
        }
        return buildUpdatePwdWithKeyResponse(ResponseType.NOT_VALID_KEY);
    }

    private UpdatePwdWithKeyResponse buildUpdatePwdWithKeyResponse(ResponseType type) {
        UpdatePwdWithKeyResponse updatePwdWithKeyResponse = new UpdatePwdWithKeyResponse();
        updatePwdWithKeyResponse.response = type;
        return updatePwdWithKeyResponse;
    }

    private boolean verifyKey(ValidationKeysDto validationKeysDto) {
        LocalDateTime now = LocalDateTime.now();
        return validationKeysDto != null
                && StringUtils.isNotEmpty(validationKeysDto.secret)
                && !validationKeysDto.used
                && validationKeysDto.creation_date.isBefore(now)
                && ChronoUnit.MINUTES.between(validationKeysDto.creation_date, now) < 10L;
    }

    private boolean isMatches(String password, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, encodedPassword);
    }


    private String[] retrieveCredentialsFromRequest(LoginRequest loginRequest) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(loginRequest.loginInfos);
        String decodedInfo = new String(decodedBytes);
        return decodedInfo.split(":");
    }

    private void updateEvent(LoginRequest loginRequest, EventsDto event) {
        if (event != null) {
            historyFacade.updateHistoryAfterFaild(event);
        } else {
            historyFacade.saveHistory(loginRequest.requestContext, ActionType.AUTHENTICATION, ActionResult.ERROR);
        }
    }

    private LoginResponse buildResponseWithJWTToken(LoginResponse loginResponse, LoginRequest loginRequest, UserDto user) throws GeneralSecurityException {

        String accessToken = jwtUtils.generateJwtToken(loginRequest.requestContext.username);
        loginResponse.accessToken = accessToken;
        loginResponse.isValidCredentials = true;
        loginResponse.isUserActive = user.enabled;
        return loginResponse;
    }

    private UpdateStatusRequest buildSystemUpdateStatus(String username) {
        UpdateStatusRequest request = new UpdateStatusRequest();
        RequestContext requestContext = new RequestContext();
        requestContext.username = username;
        requestContext.channel = "SYSTEM";
        requestContext.browserName = "";
        requestContext.osName = "";
        request.username = username;
        request.requestContext = requestContext;
        return request;
    }
}
