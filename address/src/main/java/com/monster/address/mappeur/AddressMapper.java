package com.monster.address.mappeur;

import com.monster.address.dto.AddressDetailsDto;
import com.monster.address.dto.GovernorateDto;
import com.monster.address.entity.Address;
import com.monster.address.entity.Governorate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDetailsDto mapToDto(Address address);

    GovernorateDto mapToDto(Governorate governorate);
}
