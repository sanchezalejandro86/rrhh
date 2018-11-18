package ar.com.vault.service;

import ar.com.vault.domain.Employee;
import ar.com.vault.dto.EmployeeClientDto;
import ar.com.vault.dto.EmployeeDTO;
import org.springframework.data.domain.Page;

import javax.validation.Valid;

/**
 * Created by alejandro on 23/04/18.
 */
public interface EmployeeService {
    Employee save(@Valid EmployeeClientDto employeeClientDto);

    Employee save(@Valid Employee cart);

    Employee update(Long employeeId, @Valid EmployeeClientDto employeeDto);

    void delete(Long id);

    EmployeeDTO getById(Long employeeId);

    Page<Employee> search(String jobId, Long managerId, String lastname, int page, int size);

}
