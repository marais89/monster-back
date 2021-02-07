package individu.service;

import individu.dto.ValidationKeysDto;
import individu.entity.ValidationKeys;
import individu.mappeur.ValidationKeysMapper;
import individu.repository.ValidationKeysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class ValidationKeysServiceImpl implements ValidationKeysService {

    @Autowired
    private ValidationKeysRepository validationKeysRepository;

    @Autowired
    private ValidationKeysMapper validationKeysMapper;


    @Override
    public ValidationKeysDto saveValidationKey(String login) {

        ValidationKeys validationKeys = validationKeysRepository.findValidationKeysByUsername(login);
        if (validationKeys == null) {
            validationKeys = new ValidationKeys();
        }
        validationKeys.setSecret(generateKey());
        validationKeys.setUsername(login);
        validationKeys.setCreation_date(LocalDateTime.now());
        validationKeys.setUsed(false);

        return validationKeysMapper.mapToDto(validationKeysRepository.save(validationKeys));
    }

    @Override
    public ValidationKeysDto closeKey(String login) {
        ValidationKeys validationKeys = validationKeysRepository.findValidationKeysByUsername(login);
        if (validationKeys == null) {
            return null;
        }
        validationKeys.setCreation_date(LocalDateTime.now());
        validationKeys.setUsed(true);

        return validationKeysMapper.mapToDto(validationKeysRepository.save(validationKeys));
    }

    @Override
    public ValidationKeysDto getValidationKey(String key) {
        return validationKeysMapper.mapToDto(validationKeysRepository.findValidationKeysBySecret(key));
    }

    protected String generateKey() {
        //TODO améliorer la complexité de la clé
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);

        long random = (long) (Math.random() * 999999);
        return "MONSTER$" + random + formattedDateTime;
    }
}
