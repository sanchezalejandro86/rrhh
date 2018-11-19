package ar.com.vault.department;

import ar.com.vault.AbstractIntegrationTest;
import ar.com.vault.domain.*;
import ar.com.vault.dto.DepartmentClientDto;
import ar.com.vault.dto.EmployeeClientDto;
import ar.com.vault.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class DepartmentControllerTests extends AbstractIntegrationTest {

    @Test
    public void createANewOne() throws Exception {
        long locationId = this.locationRepository.findAll().iterator().next().getId();
        long managerId = this.employeeRepository.findAll().iterator().next().getId();

        Double salaryAverage = this.employeeRepository.salaryAverage(locationId);

        assert salaryAverage < 1000; //Si es <1000 inserta en cualquier fecha

        DepartmentClientDto dto = new DepartmentClientDto("Sistemas", managerId, locationId);

        String cartJson = json(dto);

        //Create new employee
        this.mockMvc.perform(
                post("/departments")
                        .contentType(contentType).content(cartJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(dto.getName())))
                .andExpect(jsonPath("$.manager.id", is(dto.getManagerId().intValue())))
                .andExpect(jsonPath("$.location.id", is(dto.getLocationId().intValue())))
        ;
    }

    @Test
    public void testFailure() throws Exception {
        Location l1 = this.locationRepository.findAll().iterator().next();

        Department d1 = this.departmentRepository.save(new Department("Ventas", null, l1));
        Job j1 = this.jobRepository.save(new Job("TECH-LEAD", "Líder Técnico", 100d, 1000d));
        Employee e1 = this.employeeRepository.save(new Employee("Pepe", "Rodriguez", "pepe@gmail.com", "1564646464", new Date(118, 11, 1), 30000d, 1d, null, j1, d1));

        Double salaryAverage = this.employeeRepository.salaryAverage(l1.getId());

        assert salaryAverage > 1500; //Si es <1000 inserta en cualquier fecha

        DepartmentClientDto dto = new DepartmentClientDto("Sistemas", e1.getId(), l1.getId());

        String cartJson = json(dto);

        //Create new employee
        this.mockMvc.perform(
                post("/departments")
                        .contentType(contentType).content(cartJson))
                .andExpect(status().isBadRequest())
        ;
    }
}