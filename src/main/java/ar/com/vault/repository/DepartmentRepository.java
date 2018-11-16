package ar.com.vault.repository;

import ar.com.vault.domain.Department;
import ar.com.vault.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by alejandro on 16/11/18.
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
