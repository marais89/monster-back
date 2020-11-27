package hello.facade;

import hello.dto.AuthoritiesDto;
import hello.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesFacade {

    @Autowired
    private AuthoritiesService authoritiesService;

    public AuthoritiesDto retrieveAuthorities(String login) {
        return authoritiesService.retrieveUserAutorities(login);
    }


}
