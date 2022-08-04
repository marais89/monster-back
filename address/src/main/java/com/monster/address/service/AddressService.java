package com.monster.address.service;

import com.monster.address.dto.AddressDetailsDto;
import com.monster.address.dto.CountryDto;
import com.monster.address.dto.GovernorateDto;

import java.util.List;

public interface AddressService {

    List<AddressDetailsDto> findAdressByGovernoratId(Integer gouvernoratId);

    List<GovernorateDto> findGovernorateByCountryId(Integer country);

    List<CountryDto> retrieveAllCountries();
}
