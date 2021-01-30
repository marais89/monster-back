package individu.service;

import individu.dto.AuthoritiesDto;
import individu.entity.Authorities;
import individu.mappeur.AuthoritiesMapper;
import individu.repository.AuthoritiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    public AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AuthoritiesMapper authoritiesMapper;


    @Override
    public void saveAutorithies(Authorities authorities) {
        authoritiesRepository.save(authorities);
    }

    @Override
    public AuthoritiesDto retrieveUserAutorities(String login) {
        return authoritiesMapper.mapToDto(authoritiesRepository.findByUsername(login));
    }
}
