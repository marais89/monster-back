package com.monster.individu.service;

import com.monster.individu.dto.AuthoritiesDto;
import com.monster.individu.entity.Authorities;

public interface AuthoritiesService {

    void saveAutorithies(Authorities authorities);

    AuthoritiesDto retrieveUserAutorities(String username);
}
