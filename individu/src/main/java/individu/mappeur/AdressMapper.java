package individu.mappeur;

import individu.dto.AdressDto;
import individu.dto.TownDto;
import individu.entity.Adress;
import individu.entity.Town;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdressMapper {

    @Mapping(source = "town.id", target = "gouvernoratId")
    @Mapping(source = "town.name", target = "gouvernoratName")
    AdressDto mapToDto(Adress adress);

    TownDto mapToDto(Town town);
}
