package individu.repository;

import individu.entity.ValidationKeys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ValidationKeysRepository extends CrudRepository<ValidationKeys, Integer> {

    ValidationKeys findValidationKeysBySecret(String key);

    ValidationKeys findValidationKeysByUsername(String userName);
}
