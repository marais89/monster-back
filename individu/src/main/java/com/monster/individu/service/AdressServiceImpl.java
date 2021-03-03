package com.monster.individu.service;

import com.monster.individu.dto.AdressDto;
import com.monster.individu.dto.TownDto;
import com.monster.individu.mappeur.AdressMapper;
import com.monster.individu.repository.Adressrepository;
import com.monster.individu.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    @Autowired
    private Adressrepository adressrepository;

    @Autowired
    private AdressMapper adressMapper;

    @Autowired
    private TownRepository townRepository;

    @Override
    public List<AdressDto> findAdressByGouvernorat(int gouvernoratId) {
        return adressrepository.findByTown_Id(gouvernoratId)
                .stream()
                .map(adr -> adressMapper.mapToDto(adr))
                .collect(Collectors.toList());
    }

    @Override
    public List<TownDto> findAllTown() {

        List<TownDto> towns = new ArrayList<>();
        townRepository.findAll()
                .forEach(t -> towns.add(adressMapper.mapToDto(t)));
        return towns;
    }
}
