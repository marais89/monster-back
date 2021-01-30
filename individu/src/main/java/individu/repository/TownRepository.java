package individu.repository;

import individu.entity.Town;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface TownRepository extends CrudRepository<Town, Integer> {
}
