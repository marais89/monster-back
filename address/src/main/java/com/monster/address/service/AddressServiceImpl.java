package com.monster.address.service;


import com.monster.address.dto.AddressDetailsDto;
import com.monster.address.dto.CountryDto;
import com.monster.address.dto.GovernorateDto;
import com.monster.address.mappeur.AddressMapper;
import com.monster.address.mappeur.CountryMapper;
import com.monster.address.mappeur.GovernorateMapper;
import com.monster.address.repository.AddressDetailsRepository;
import com.monster.address.repository.CountryRepository;
import com.monster.address.repository.GovernorateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDetailsRepository addressDetailsrepository;

    @Autowired
    private GovernorateRepository governorateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private GovernorateMapper governorateMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressDetailsDto> findAdressByGovernoratId(Integer governoratId) {

        return addressDetailsrepository.findByGovernorate_Id(governoratId)
                .stream()
                .map(adr -> addressMapper.mapToDto(adr))
                .collect(Collectors.toList());
    }

    @Override
    public List<GovernorateDto> findGovernorateByCountryId(Integer countryId) {

        return governorateRepository.findByCountry_Id(countryId)
                .stream()
                .map(governorateMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<CountryDto> retrieveAllCountries() {

        List<CountryDto> countryDtoList = new ArrayList<>();
        countryRepository.findAll().forEach(c -> countryDtoList.add(countryMapper.mapToDto(c)));
        return countryDtoList;
    }
}
