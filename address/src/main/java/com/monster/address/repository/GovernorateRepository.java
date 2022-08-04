package com.monster.address.repository;

import com.monster.address.entity.Governorate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GovernorateRepository extends CrudRepository<Governorate, Integer> {

    List<Governorate> findByCountry_Id(Integer country);
}
