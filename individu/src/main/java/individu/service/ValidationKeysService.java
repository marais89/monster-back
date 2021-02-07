package individu.service;

import individu.dto.ValidationKeysDto;

public interface ValidationKeysService {

    ValidationKeysDto saveValidationKey(String login);

    ValidationKeysDto closeKey(String login);

    ValidationKeysDto getValidationKey(String key);
}
