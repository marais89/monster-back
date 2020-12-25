package hello.repository;

import hello.entity.Adress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface Adressrepository extends CrudRepository<Adress, Integer> {

    public List<Adress> findByGouvernorat_NameInOrVilleInOrCiteIn(List<String> keys1, List<String> keys2, List<String> keys3);
}
