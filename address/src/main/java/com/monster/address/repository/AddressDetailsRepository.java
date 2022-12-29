package com.monster.address.repository;

import com.monster.address.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AddressDetailsRepository  {

    List<Address> findByGovernorate_Id(int gouvernoratId);
}
