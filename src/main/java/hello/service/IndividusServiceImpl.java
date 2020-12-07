package hello.service;

import hello.dto.IndividuDto;
import hello.entity.Authorities;
import hello.entity.User;
import hello.mappeur.IndividusMapper;
import hello.repository.AuthoritiesRepository;
import hello.repository.IndividuRepository;
import hello.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
    //TODO remove unused code
    public IndividuDto saveOrUpdate(IndividuDto individuDto) {
        return individusMapper.mapToDto(individuRepository.save(individusMapper.mapToEntity(individuDto)));
        //return individuRepository.findById(individuDto.id).get(0));
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
        user.password = individu.pass;
        user.username = individu.email;
        user.enabled = true;
        return user;
    }

    private Authorities buildAuthorities(IndividuDto individu) {
        Authorities authorities = new Authorities();
        authorities.username = individu.email;
        authorities.authority = "ROLE_USER";
        return authorities;
    }

}
