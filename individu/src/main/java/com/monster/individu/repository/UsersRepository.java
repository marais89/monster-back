package com.monster.individu.repository;

import com.monster.individu.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, Integer> {

    @Query("{username:'?0'}")
    User findByUsername(String username);

}
