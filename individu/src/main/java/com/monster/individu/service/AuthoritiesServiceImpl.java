package com.monster.individu.service;

import com.monster.individu.dto.AuthoritiesDto;
import com.monster.individu.entity.Authorities;
import com.monster.individu.mappeur.AuthoritiesMapper;
import com.monster.individu.repository.AuthoritiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    public AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AuthoritiesMapper authoritiesMapper;


    @Override
    public void saveAutorithies(Authorities authorities) {
        authoritiesRepository.save(authorities);
    }

    @Override
    public AuthoritiesDto retrieveUserAutorities(String username) {
        return authoritiesMapper.mapToDto(authoritiesRepository.findByUsername(username));
    }
}
