package individu.repository;

import individu.entity.Adress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface Adressrepository extends CrudRepository<Adress, Integer> {

    public List<Adress> findByTown_Id(int gouvernoratId);
}
