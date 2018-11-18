package ar.com.vault.employee;

import ar.com.vault.AbstractIntegrationTest;
import ar.com.vault.domain.*;
import ar.com.vault.dto.EmployeeClientDto;
import ar.com.vault.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by alejandro on 18/11/18.
 */
public class EmployeeControllerTests extends AbstractIntegrationTest {

    @Test
    public void createANewOne() throws Exception {
        EmployeeClientDto dto = new EmployeeClientDto(
                "Alejandro",
                "Sánchez",
                "ale@gmail.com",
                "1540553457",
                new Date(118, 11, 18),
                1000d,
                1d,
                "TECH-LEAD"
                );
        String cartJson = json(dto);

        //Create new employee
        this.mockMvc.perform(
                post("/employees")
                        .contentType(contentType).content(cartJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.firstname", is(dto.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(dto.getLastname())))
                .andExpect(jsonPath("$.email", is(dto.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(dto.getPhoneNumber())))
//                .andExpect(jsonPath("$.hireDate", is(dto.getHireDate())))
                .andExpect(jsonPath("$.salary", is(dto.getSalary())))
                .andExpect(jsonPath("$.commisionPct", is(dto.getCommisionPct())))
        ;
    }

    @Test
    public void updateAnOldOne() throws Exception {
        long updatedId = this.employeeRepository.findAll().iterator().next().getId();

        EmployeeClientDto dto = new EmployeeClientDto(
                "Ramon",
                "Perez",
                "ramonperez@gmail.com",
                "99999999",
                new Date(115, 11, 18),
                3000d,
                4d,
                "DEV-SR"
        );
        String cartJson = json(dto);

        //Update first employee
        this.mockMvc.perform(
                put("/employees/" + updatedId)
                        .contentType(contentType).content(cartJson))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(updatedId)))
                .andExpect(jsonPath("$.firstname", is(dto.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(dto.getLastname())))
                .andExpect(jsonPath("$.email", is(dto.getEmail())))
                .andExpect(jsonPath("$.phoneNumber", is(dto.getPhoneNumber())))
//                .andExpect(jsonPath("$.hireDate", is(dto.getHireDate())))
                .andExpect(jsonPath("$.salary", is(dto.getSalary())))
                .andExpect(jsonPath("$.commisionPct", is(dto.getCommisionPct())))
        ;
    }

    @Test
    public void removeEmployee() throws Exception {
        Department d1 = this.departmentRepository.findAll().iterator().next();
        Job j1 = this.jobRepository.findAll().iterator().next();
        Employee toRemove = this.employeeRepository.save(new Employee("Marcos", "Peralta", "mperalta@gmail.com", "1564646464", new Date(118, 11, 1), 1000d, 1d, null, j1, d1));

        // Remove employee 1
        this.mockMvc.perform(
                delete("/employees/" + toRemove.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getPagedEmployees() throws Exception {

        Iterator<Employee> iterator = this.employeeRepository.findAll().iterator();

        Employee employeeToSearch = null;
        while(iterator.hasNext()){
            Employee e = iterator.next();
            if(e.getManager() != null){
                employeeToSearch = e;
                break;
            }
        }

        // Search Employees
        this.mockMvc.perform(
                get("/employees?job_id="+employeeToSearch.getJob().getId() +
                        "&manager_id="+employeeToSearch.getManager().getId()+
                        "&lastname="+employeeToSearch.getLastname()+
                        "&page=1&size=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)))
                .andExpect(jsonPath("$.content[0].id", is(employeeToSearch.getId())))
        ;
    }

}