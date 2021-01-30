package individu.mappeur;

import individu.dto.AuthoritiesDto;
import individu.entity.Authorities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthoritiesMapper {

    Authorities mapToEntity(AuthoritiesDto authoritiesDto);

    AuthoritiesDto mapToDto(Authorities authorities);
}
