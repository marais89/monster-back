package com.monster.address.repository;

import com.monster.address.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDetailsRepository extends CrudRepository<Address, Integer> {

    List<Address> findByGovernorate_Id(int gouvernoratId);
}
