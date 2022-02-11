package com.monster.business.repository;

import com.monster.business.entity.Business;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository extends CrudRepository<Business, Integer> {

    List<Business> findBusinessByCreatorId(int creator);
}
