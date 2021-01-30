package individu.service;

import individu.dto.AdressDto;
import individu.dto.TownDto;

import java.util.List;

public interface AdressService {

    public List<AdressDto> findAdressByGouvernorat(int gouvernoratId);

    public List<TownDto> findAllTown();
}
