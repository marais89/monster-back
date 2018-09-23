package hello.service;

import hello.entity.Individu;
import hello.repository.IndividuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndividusServiceImpl implements IndividusService {

    @Autowired
    private IndividuRepository individuRepository;


    @Override
    public void save(hello.dto.Individu individu) {
        individuRepository.save(mapToentity(individu));
    }

    @Override
    public List<hello.dto.Individu> findAll() {

        List<hello.dto.Individu> individus = new ArrayList<>();
        individuRepository.findAll().forEach(individu -> individus.add(mapToDto(individu)));
        return individus;
    }

    @Override
    public List<hello.dto.Individu> findByEmail(String email) {
        List<hello.dto.Individu> individus = new ArrayList<>();
        individuRepository.findByEmail(email).stream().forEach(individu -> individus.add(mapToDto(individu)));
        return individus;
    }

    @Override
    public List<hello.dto.Individu> findByNumeroTel(Integer numero) {
        List<hello.dto.Individu> individus = new ArrayList<>();
        individuRepository.findByNumeroTel(numero).stream().forEach(individu -> individus.add(mapToDto(individu)));
        return individus;    }

    private Individu mapToentity(hello.dto.Individu individuDto){
        Individu individuEntity = new Individu();
        individuEntity.setNom(individuDto.getNom());
        individuEntity .setPrenom(individuDto.getPrenom());
        individuEntity.setAdresse(individuDto.getAdresse());
        individuEntity.setDate_naissance(individuDto.getDateNaissance());
        individuEntity.setNumeroTel(individuDto.getNumTel());
        individuEntity.setEmail(individuDto.getEmail());
        return individuEntity;
    }

    private hello.dto.Individu mapToDto(Individu individuEntity){
        hello.dto.Individu individuDto = new hello.dto.Individu();
        individuDto.setId(individuEntity.getId());
        individuDto.setNom(individuEntity.getNom());
        individuDto.setPrenom(individuEntity.getPrenom());
        individuDto.setEmail(individuEntity.getEmail());
        individuDto.setNumTel(individuEntity.getNumeroTel());
        individuDto.setAdresse(individuEntity.getAdresse());
        individuDto.setDateNaissance(individuEntity.getDate_naissance());
        return  individuDto;
    }
}
