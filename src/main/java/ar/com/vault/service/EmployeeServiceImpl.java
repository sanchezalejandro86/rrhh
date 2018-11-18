package ar.com.vault.service;

import ar.com.vault.domain.Department;
import ar.com.vault.domain.Employee;
import ar.com.vault.domain.Job;
import ar.com.vault.dto.EmployeeClientDto;
import ar.com.vault.dto.EmployeeDTO;
import ar.com.vault.exception.DomainEntityNotFound;
import ar.com.vault.repository.DepartmentRepository;
import ar.com.vault.repository.EmployeeRepository;
import ar.com.vault.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alejandro on 17/11/18.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final JobRepository jobRepository;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Employee save(@Valid EmployeeClientDto dto) {
        Employee employee = new Employee();
        this.mapFromClientDto(employee, dto);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee save(@Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long employeeId, @Valid EmployeeClientDto dto) {
        Employee e = this.employeeRepository.findById(employeeId).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Empleado con id: " + employeeId));

        this.mapFromClientDto(e, dto);
        return employeeRepository.save(e);
    }

    @Override
    public void delete(Long id) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Empleado con id: " + id));
        this.employeeRepository.delete(employee);
    }

    @Override
    public Page<Employee> search(String jobId, Long managerId, String lastname, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = this.employeeRepository.search(jobId, managerId, lastname, pageable);

        List<Employee> employeesSorted = employeePage.stream().sorted((e1, e2) -> {
            if (e1.getHireDate().before(e2.getHireDate())) {
                return 1;
            } else if (e1.getHireDate().after(e2.getHireDate())) {
                return -1;
            }
            return 0;
        }).collect(Collectors.toList());

        return new PageImpl(employeesSorted, pageable, employeePage.getTotalElements());
    }

    @Override
    public EmployeeDTO getById(Long id){
        Employee e = this.employeeRepository.findById(id).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Empleado con id: " + id));

        return new EmployeeDTO(
                e.getId(),
                e.getFirstname(),
                e.getLastname(),
                e.getEmail(),
                e.getPhoneNumber(),
                e.getHireDate(),
                e.getSalary(),
                e.getCommisionPct()
        );
    }

    private Employee mapFromClientDto(Employee employee, @Valid EmployeeClientDto dto) {
        employee.setFirstname(dto.getFirstname());
        employee.setLastname(dto.getLastname());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setHireDate(dto.getHireDate());
        employee.setSalary(dto.getSalary());
        employee.setCommisionPct(dto.getCommisionPct());

        Job job = this.jobRepository.findById(dto.getJobId()).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Job con id: " + dto.getJobId()));
        employee.setJob(job);

        if(dto.getManagerId() != null){
            Employee manager = this.employeeRepository.findById(dto.getManagerId()).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Manager con id: " + dto.getManagerId()));
            employee.setManager(manager);
        }
        if(dto.getDepartmentId() != null) {
            Department department = this.departmentRepository.findById(dto.getDepartmentId()).orElseThrow(() -> new DomainEntityNotFound("No se encontró el Departamento con id: " + dto.getDepartmentId()));
            employee.setDepartment(department);
        }
        return employee;
    }
}
