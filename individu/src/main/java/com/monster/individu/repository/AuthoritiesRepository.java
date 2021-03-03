package com.monster.individu.repository;

import com.monster.individu.entity.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Integer> {

    Authorities findByUsername(String username);

}
