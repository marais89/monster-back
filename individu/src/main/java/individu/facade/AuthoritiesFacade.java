package individu.facade;

import individu.dto.AuthoritiesDto;
import individu.service.AuthoritiesService;
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
