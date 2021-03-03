package com.monster.individu.repository;

import com.monster.individu.entity.Town;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends CrudRepository<Town, Integer> {
}
