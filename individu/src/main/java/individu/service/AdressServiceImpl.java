package individu.service;

import individu.dto.AdressDto;
import individu.dto.TownDto;
import individu.mappeur.AdressMapper;
import individu.repository.Adressrepository;
import individu.repository.TownRepository;
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
                .map(adr -> adressMapper.mapToDto(adr)).collect(Collectors.toList());
    }

    @Override
    public List<TownDto> findAllTown() {

        List<TownDto> towns = new ArrayList<>();
        townRepository.findAll().forEach(t -> towns.add(adressMapper.mapToDto(t)));
        return towns;
    }
}
