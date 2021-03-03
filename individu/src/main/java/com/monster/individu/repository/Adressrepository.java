package com.monster.individu.repository;

import com.monster.individu.entity.Adress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Adressrepository extends CrudRepository<Adress, Integer> {

    List<Adress> findByTown_Id(int gouvernoratId);
}
