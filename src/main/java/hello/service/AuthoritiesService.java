package hello.service;

import hello.dto.AuthoritiesDto;
import hello.entity.Authorities;

public interface AuthoritiesService {

    public void saveAutorithies(Authorities authorities);

    public AuthoritiesDto retrieveUserAutorities(String login);
}
