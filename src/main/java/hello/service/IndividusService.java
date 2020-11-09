package hello.service;

import hello.dto.IndividuDto;
import hello.entity.Individu;

import java.util.List;

public interface IndividusService {

    void save(IndividuDto individuDto);

    IndividuDto update(IndividuDto individuDto);

    List<IndividuDto> findAll();

    IndividuDto findByEmail(String email);

    List<IndividuDto> findByNumeroTel(String numero);

    void delete(Long id);

}
