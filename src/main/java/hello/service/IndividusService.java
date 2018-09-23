package hello.service;

import java.util.List;

public interface IndividusService {

    void save(hello.dto.Individu individu);

    List<hello.dto.Individu> findAll();

    List<hello.dto.Individu> findByEmail(String email);

    List<hello.dto.Individu> findByNumeroTel(Integer numero);
}
