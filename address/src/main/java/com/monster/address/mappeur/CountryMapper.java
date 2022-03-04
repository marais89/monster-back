package com.monster.address.mappeur;

import com.monster.address.dto.CountryDto;
import com.monster.address.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDto mapToDto(Country country);

    Country mapToEntity(CountryDto countryDto);
}
