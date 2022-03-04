package com.monster.address.mappeur;

import com.monster.address.dto.GovernorateDto;
import com.monster.address.entity.Governorate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GovernorateMapper {

    GovernorateDto mapToDto(Governorate governorate);

    Governorate mapToEntity(GovernorateDto governorateDto);
}
