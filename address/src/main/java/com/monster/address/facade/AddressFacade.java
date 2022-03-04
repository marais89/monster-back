package com.monster.address.facade;

import com.monster.address.dto.AddressDetailsDto;
import com.monster.address.dto.CountryDto;
import com.monster.address.dto.GovernorateDto;
import com.monster.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressFacade {

    @Autowired
    private AddressService addressService;

    public List<AddressDetailsDto> findAdressByGouvernorat(int gouvernoratId) {
        return addressService.findAdressByGovernoratId(gouvernoratId);
    }

    public List<GovernorateDto> findGovernorateByCountryId(int country) {
        return addressService.findGovernorateByCountryId(country);
    }

    public List<CountryDto> retrieveAllCountries() {
        return addressService.retrieveAllCountries();
    }
}
