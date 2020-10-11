package hello.service;

import hello.entity.Authorities;
import hello.repository.AuthoritiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthoritiesServiceImpl implements AuthoritiesService {

    @Autowired
    public AuthoritiesRepository authoritiesRepository;


    @Override
    public void saveAutorithies(Authorities authorities) {
        authoritiesRepository.save(authorities);
    }
}
