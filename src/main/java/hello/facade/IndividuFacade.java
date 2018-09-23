package hello.facade;

import hello.dto.Individu;
import hello.service.IndividusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndividuFacade {

    @Autowired
    private IndividusService individusService;


    public List<Individu> findAll() {
        return individusService.findAll();
    }

    public List<Individu> getByEmail(String email) {
        return individusService.findByEmail(email);
    }

    public List<Individu> getByNumeroTel(Integer numero) {
        return individusService.findByNumeroTel(numero);
    }

    public void save(Individu individu){
        individusService.save(individu);
    }
}
