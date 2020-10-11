package hello.service;

import hello.dto.IndividuDto;

import java.util.List;

public interface IndividusService {

    void save(IndividuDto individuDto);

    List<IndividuDto> findAll();

    List<IndividuDto> findByEmail(String email);

    List<IndividuDto> findByNumeroTel(String numero);

    void delete(Long id);

}
