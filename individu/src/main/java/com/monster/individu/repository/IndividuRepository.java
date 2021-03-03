package com.monster.individu.repository;


import com.monster.individu.entity.Individu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividuRepository extends CrudRepository<Individu, Integer> {

    List<Individu> findByEmail(String email);

    Individu findByUsername(String username);

    List<Individu> findByNumeroTel(String numero);

    List<Individu> findById(Long id);

    void deleteIndividuById(Long id);
}

