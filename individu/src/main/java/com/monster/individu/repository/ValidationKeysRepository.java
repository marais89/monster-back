package com.monster.individu.repository;

import com.monster.individu.entity.ValidationKeys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationKeysRepository extends CrudRepository<ValidationKeys, Integer> {

    ValidationKeys findValidationKeysBySecret(String key);

    ValidationKeys findValidationKeysByUsername(String userName);
}
