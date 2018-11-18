package ar.com.vault.repository;

import ar.com.vault.domain.Country;
import ar.com.vault.domain.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alejandro on 17/11/18.
 */
@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {

}
