package com.monster.address.service;

import com.monster.address.dto.AddressDetailsDto;
import com.monster.address.dto.CountryDto;
import com.monster.address.dto.GovernorateDto;

import java.util.List;

public interface AddressService {

    List<AddressDetailsDto> findAdressByGovernoratId(int gouvernoratId);

    List<GovernorateDto> findGovernorateByCountryId(int country);

    List<CountryDto> retrieveAllCountries();
}
