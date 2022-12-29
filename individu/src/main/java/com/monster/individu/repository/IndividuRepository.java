package com.monster.individu.repository;


import com.monster.individu.entity.Individu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndividuRepository extends MongoRepository<Individu, Integer> {

    @Query("{email:'?0'}")
    List<Individu> findByEmail(String email);

    @Query("{username:'?0'}")
    Individu findByUsername(String username);

    @Query("{numero:'?0'}")
    List<Individu> findByNumeroTel(String numero);

    @Query("{id:'?0'}")
    List<Individu> findById(Long id);

    @Query("{id:'?0'}")
    void deleteIndividuById(Long id);
}

