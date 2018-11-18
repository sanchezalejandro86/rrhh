package ar.com.vault.repository;

import ar.com.vault.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alejandro on 16/11/18.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.job.id = ?1 OR e.manager.id = ?2 OR e.lastname =?3")
    Page<Employee> search(String jobId, Long managerId, String lastname, Pageable pageable);
}
