package hello.mappeur;

import hello.dto.AdressDto;
import hello.entity.Adress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdressMapper {

    @Mapping(source = "gouvernorat.id", target = "gouvernoratId")
    @Mapping(source = "gouvernorat.name", target = "gouvernoratName")
    AdressDto mapToDto(Adress adress);
}
