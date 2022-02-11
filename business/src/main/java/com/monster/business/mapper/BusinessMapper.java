package com.monster.business.mapper;

import com.monster.business.dto.BusinessDto;
import com.monster.business.entity.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    @Mapping(source = "business.status", target = "status")
    BusinessDto mapToDto(Business business);

    Business mapToEntity(BusinessDto businessDto);

}
