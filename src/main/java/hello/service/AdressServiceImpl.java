package hello.service;

import hello.dto.AdressDto;
import hello.mappeur.AdressMapper;
import hello.repository.Adressrepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    @Autowired
    private Adressrepository adressrepository;

    @Autowired
    private AdressMapper adressMapper;

    @Override
    public List<AdressDto> findAdress(List<String> keys) {
        return adressrepository.findByGouvernorat_NameInOrVilleInOrCiteIn(keys, keys, keys).stream().map(adr -> adressMapper.mapToDto(adr)).collect(Collectors.toList());
    }
}
