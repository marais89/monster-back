package com.monster.individu.service;

import com.monster.individu.dto.IndividuDto;

import java.util.List;

public interface IndividusService {

    void saveNewUser(IndividuDto individuDto);

    IndividuDto saveOrUpdate(IndividuDto individuDto);

    IndividuDto findById(Long id);

    List<IndividuDto> findAll();

    IndividuDto findByEmail(String email);

    IndividuDto findByUsername(String username);


    List<IndividuDto> findByNumeroTel(String numero);

    void delete(Long id);

}
