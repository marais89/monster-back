package com.monster.individu.mappeur;

import com.monster.individu.dto.ValidationKeysDto;
import com.monster.individu.entity.ValidationKeys;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValidationKeysMapper {

    ValidationKeys mapToEntity(ValidationKeysDto validationKeysDto);

    ValidationKeysDto mapToDto(ValidationKeys validationKeys);
}
