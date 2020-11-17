package hello.service;

import hello.dto.IndividuDto;
import hello.entity.Individu;

import java.util.List;

public interface IndividusService {

    void saveNewUser(IndividuDto individuDto);

    IndividuDto saveOrUpdate(IndividuDto individuDto);

    IndividuDto findById(Long id);

    List<IndividuDto> findAll();

    IndividuDto findByEmail(String email);

    List<IndividuDto> findByNumeroTel(String numero);

    void delete(Long id);

}
