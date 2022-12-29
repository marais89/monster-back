package com.monster.individu.repository;

import com.monster.individu.entity.Authorities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends MongoRepository<Authorities, Integer> {

    @Query("{username:'?0'}")
    Authorities findByUsername(String username);

}
