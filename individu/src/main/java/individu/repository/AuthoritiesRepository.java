package individu.repository;

import individu.entity.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Integer> {

    public Authorities findByUsername(String username);

}
