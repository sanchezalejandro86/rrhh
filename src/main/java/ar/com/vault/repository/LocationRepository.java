package ar.com.vault.repository;

import ar.com.vault.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alejandro on 17/11/18.
 */
@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
