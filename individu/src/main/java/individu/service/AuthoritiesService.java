package individu.service;

import individu.dto.AuthoritiesDto;
import individu.entity.Authorities;

public interface AuthoritiesService {

    public void saveAutorithies(Authorities authorities);

    public AuthoritiesDto retrieveUserAutorities(String login);
}
