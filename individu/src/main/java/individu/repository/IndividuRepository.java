package individu.repository;


import individu.entity.Individu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface IndividuRepository extends CrudRepository<Individu, Integer> {

    public List<Individu> findByEmail(String email);

    public List<Individu> findByNumeroTel(String numero);

    public List<Individu> findById(Long id);

    public void deleteIndividuById(Long id);
}
