package hello.service;

import hello.dto.AdressDto;
import hello.dto.TownDto;
import hello.entity.Town;

import java.util.List;

public interface AdressService {

    public List<AdressDto> findAdressByGouvernorat(int gouvernoratId);

    public List<TownDto> findAllTown();
}
