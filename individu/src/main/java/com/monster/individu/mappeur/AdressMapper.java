package com.monster.individu.mappeur;

import com.monster.individu.dto.TownDto;
import com.monster.individu.entity.Adress;
import com.monster.individu.entity.Town;
import com.monster.individu.dto.AdressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdressMapper {

    @Mapping(source = "town.id", target = "gouvernoratId")
    @Mapping(source = "town.name", target = "gouvernoratName")
    AdressDto mapToDto(Adress adress);

    TownDto mapToDto(Town town);
}
