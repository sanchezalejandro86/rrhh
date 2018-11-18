package ar.com.vault.service;

import ar.com.vault.domain.Department;
import ar.com.vault.domain.Employee;
import ar.com.vault.domain.Location;
import ar.com.vault.dto.DepartmentClientDto;
import ar.com.vault.dto.EmployeeClientDto;
import ar.com.vault.dto.EmployeeDTO;
import ar.com.vault.exception.DomainEntityNotFound;
import ar.com.vault.repository.DepartmentRepository;
import ar.com.vault.repository.EmployeeRepository;
import ar.com.vault.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alejandro on 23/04/18.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 EmployeeRepository employeeRepository,
                                 LocationRepository locationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Department save(@Valid DepartmentClientDto dto) {
        Department department = new Department();
        this.mapFromClientDto(department, dto);

        //TODO check conditions before insert

        return this.departmentRepository.save(department);
    }

    private void mapFromClientDto(Department department, @Valid DepartmentClientDto dto) {
        Employee manager = this.employeeRepository.findById(dto.getManagerId()).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Manager con id: " + dto.getManagerId()));
        Location location = this.locationRepository.findById(dto.getLocationId()).orElseThrow(() -> new DomainEntityNotFound("No se encontró la ubicación con id: " + dto.getLocationId()));

        department.setManager(manager);
        department.setLocation(location);
        department.setName(dto.getName());
    }
}
