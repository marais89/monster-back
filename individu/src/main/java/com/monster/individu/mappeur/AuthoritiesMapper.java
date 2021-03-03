package com.monster.individu.mappeur;

import com.monster.individu.dto.AuthoritiesDto;
import com.monster.individu.entity.Authorities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthoritiesMapper {

    Authorities mapToEntity(AuthoritiesDto authoritiesDto);

    AuthoritiesDto mapToDto(Authorities authorities);
}
