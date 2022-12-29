package com.monster.individu.repository;

import com.monster.individu.entity.ValidationKeys;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationKeysRepository extends MongoRepository<ValidationKeys, Integer> {

    @Query("{key:'?0'}")
    ValidationKeys findValidationKeysBySecret(String key);

    @Query("{username:'?0'}")
    ValidationKeys findValidationKeysByUsername(String userName);
}
