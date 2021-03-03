package com.monster.individu.facade;

import com.monster.individu.dto.AuthoritiesDto;
import com.monster.individu.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesFacade {

    @Autowired
    private AuthoritiesService authoritiesService;

    public AuthoritiesDto retrieveAuthorities(String username) {
        return authoritiesService.retrieveUserAutorities(username);
    }


}
