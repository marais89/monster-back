package hello.mappeur;

import hello.dto.AuthoritiesDto;
import hello.entity.Authorities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthoritiesMapper {

    Authorities mapToEntity(AuthoritiesDto authoritiesDto);

    AuthoritiesDto mapToDto(Authorities authorities);
}
