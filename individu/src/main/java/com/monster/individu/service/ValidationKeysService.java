package com.monster.individu.service;

import com.monster.individu.dto.ValidationKeysDto;

public interface ValidationKeysService {

    ValidationKeysDto saveValidationKey(String username);

    ValidationKeysDto closeKey(String username);

    ValidationKeysDto getValidationKey(String key);
}
