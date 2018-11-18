package ar.com.vault.service;

import ar.com.vault.domain.Department;
import ar.com.vault.dto.DepartmentClientDto;

import javax.validation.Valid;

/**
 * Created by alejandro on 23/04/18.
 */
public interface DepartmentService {
    Department save(@Valid DepartmentClientDto departmentClientDto);

}
