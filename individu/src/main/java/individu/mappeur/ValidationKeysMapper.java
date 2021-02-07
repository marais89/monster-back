package individu.mappeur;

import individu.dto.ValidationKeysDto;
import individu.entity.ValidationKeys;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValidationKeysMapper {

    ValidationKeys mapToEntity(ValidationKeysDto validationKeysDto);

    ValidationKeysDto mapToDto(ValidationKeys validationKeys);
}
