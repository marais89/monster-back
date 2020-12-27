package hello.mappeur;

import hello.dto.AdressDto;
import hello.dto.TownDto;
import hello.entity.Adress;
import hello.entity.Town;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdressMapper {

    @Mapping(source = "town.id", target = "gouvernoratId")
    @Mapping(source = "town.name", target = "gouvernoratName")
    AdressDto mapToDto(Adress adress);

    TownDto mapToDto(Town town);
}
