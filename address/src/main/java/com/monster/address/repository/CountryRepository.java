package com.monster.address.repository;

import com.monster.address.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
}
