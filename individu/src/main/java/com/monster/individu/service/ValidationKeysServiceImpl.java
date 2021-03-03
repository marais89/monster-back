package com.monster.individu.service;

import com.monster.individu.dto.ValidationKeysDto;
import com.monster.individu.entity.ValidationKeys;
import com.monster.individu.mappeur.ValidationKeysMapper;
import com.monster.individu.repository.ValidationKeysRepository;
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
    public ValidationKeysDto saveValidationKey(String username) {

        ValidationKeys validationKeys = validationKeysRepository.findValidationKeysByUsername(username);
        if (validationKeys == null) {
            validationKeys = new ValidationKeys();
        }
        validationKeys.setSecret(generateKey());
        validationKeys.setUsername(username);
        validationKeys.setCreation_date(LocalDateTime.now());
        validationKeys.setUsed(false);

        return validationKeysMapper.mapToDto(validationKeysRepository.save(validationKeys));
    }

    @Override
    public ValidationKeysDto closeKey(String username) {
        ValidationKeys validationKeys = validationKeysRepository.findValidationKeysByUsername(username);
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
