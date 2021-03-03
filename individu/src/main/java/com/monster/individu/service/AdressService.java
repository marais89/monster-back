package com.monster.individu.service;

import com.monster.individu.dto.AdressDto;
import com.monster.individu.dto.TownDto;

import java.util.List;

public interface AdressService {

    public List<AdressDto> findAdressByGouvernorat(int gouvernoratId);

    public List<TownDto> findAllTown();
}
