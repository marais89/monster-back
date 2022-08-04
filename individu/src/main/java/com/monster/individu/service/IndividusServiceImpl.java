package com.monster.individu.service;

import com.monster.individu.dto.IndividuDto;
import com.monster.individu.entity.Authorities;
import com.monster.individu.entity.Individu;
import com.monster.individu.entity.User;
import com.monster.individu.mappeur.IndividusMapper;
import com.monster.individu.repository.AuthoritiesRepository;
import com.monster.individu.repository.IndividuRepository;
import com.monster.individu.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndividusServiceImpl implements IndividusService {


    @Autowired
    private IndividusMapper individusMapper;

    @Autowired
    private IndividuRepository individuRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public void saveNewUser(IndividuDto individuDto) {
        individuRepository.save(individusMapper.mapToEntity(individuDto));
        usersRepository.save(buildUser(individuDto));
        authoritiesRepository.save(buildAuthorities(individuDto));
    }

    @Override
    public IndividuDto saveOrUpdate(IndividuDto individuDto) {
        return individusMapper.mapToDto(individuRepository.save(individusMapper.mapToEntity(individuDto)));
    }

    @Override
    public IndividuDto findById(Long id) {
        return individusMapper.mapToDto(individuRepository.findById(id).stream().findFirst().orElse(null));
    }

    @Override
    public List<IndividuDto> findAll() {

        List<IndividuDto> individuses = new ArrayList<>();
        individuRepository.findAll().forEach(individu -> individuses.add(individusMapper.mapToDto(individu)));
        return individuses;
    }

    @Override
    public IndividuDto findByEmail(String email) {
        List<IndividuDto> individuses = new ArrayList<>();
        individuRepository.findByEmail(email).stream().forEach(individu -> individuses.add(individusMapper.mapToDto(individu)));
        return individuses.stream().findFirst().orElse(null);
    }

    @Override
    public IndividuDto findByUsername(String username) {
        Individu individu = individuRepository.findByUsername(username);
        if (individu != null) {
            return individusMapper.mapToDto(individu);
        }
        return null;
    }

    @Override
    public List<IndividuDto> findByNumeroTel(String numero) {
        List<IndividuDto> individuses = new ArrayList<>();
        individuRepository.findByNumeroTel(numero).stream().forEach(individu -> individuses.add(individusMapper.mapToDto(individu)));
        return individuses;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        individuRepository.deleteIndividuById(id);
    }

    private User buildUser(IndividuDto individu) {
        User user = new User();
        user.setPassword(individu.pass);
        user.setUsername(individu.username);
        user.setEnabled(true);
        return user;
    }

    private Authorities buildAuthorities(IndividuDto individu) {
        Authorities authorities = new Authorities();
        authorities.setUsername(individu.username);
        authorities.setAuthority("ROLE_USER");
        return authorities;
    }

}
