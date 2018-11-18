package ar.com.vault.seed;

import ar.com.vault.domain.*;
import ar.com.vault.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.logging.Logger;

/**
 * Created by alejandro on 18/11/18.
 */
@Profile("!test")
@Component
public class LocalApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    protected EmployeeRepository employeeRepository;

    @Autowired
    protected JobRepository jobRepository;

    @Autowired
    protected DepartmentRepository departmentRepository;

    @Autowired
    protected LocationRepository locationRepository;

    @Autowired
    protected CountryRepository countryRepository;

    @Autowired
    protected RegionRepository regionRepository;

    final Logger logger = Logger.getLogger("LocalApplicationStartup");

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        logger.info("Starting seed");

        //Regions
        logger.info("REGIONS:");
        Region r1 = this.createRegion("SUDAMERICA");
        Region r2 = this.createRegion("NORTEAMERICA");
        Region r3 = this.createRegion("EUROPA");

        //Countries
        logger.info("COUNTRIES:");
        Country c1 = this.createCountry("AR", "ARGENTINA", r1);
        Country c2 = this.createCountry("BR", "BRASIL", r1);

        logger.info("LOCATIONS:");
        Location l1 = this.createLocation("RIVADAVIA 5000", "1424", "CABA", "CABA", c1);
        Location l2 = this.createLocation("FLORIDA 1", "1065", "CABA", "CABA", c1);

        logger.info("DEPARTMENTS:");
        Department d1 = this.createDepartment("RRHH", null, l1);
        Department d2 = this.createDepartment("COMERCIAL", null, l1);
        Department d3 = this.createDepartment("SISTEMAS", null, l2);

        logger.info("JOBS:");
        Job j1 = this.createJob("CTO", "CTO", 800, 1500);
        Job j2 = this.createJob("AN-JR", "ANALISTA JR.", 300, 400);
        Job j3 = this.createJob("DEV-SR", "DESARROLLADOR SR.", 400, 600);
        Job j4 = this.createJob("TECH-LEAD", "TECH LEAD", 600, 700);

        logger.info("EMPLOYEES:");
        Employee e1 = this.createEmployee("ALE", "SANCHEZ", "ALE@GMAIL.COM", "1540554354", new Date(108, 11, 10), 1200d, 25d, null, j1, d3);
        Employee e2 = this.createEmployee("RAUL", "GONZALEZ", "R@GMAIL.COM", "1521115656", new Date(108, 6, 1), 500d, 10d, e1, j3, d3);
        Employee e3 = this.createEmployee("JUAN", "PEREZ", "J@PEREZ.COM", "1522254665", new Date(106, 11, 2), 500d, 0d, null, j2, d1);

        logger.info("Seeder finished successfully");
    }

    private Employee createEmployee(String firstname, String lastname, String email, String phone, Date hireDate, Double salary, Double commisionPct, Employee manager, Job job, Department department) {
        Employee e = this.employeeRepository.save(new Employee(firstname, lastname, email, phone, hireDate, salary, commisionPct, manager, job, department));
        logger.info(e.getId() + " | " + e.getFirstname() + " | " + e.getLastname() + " | " + e.getEmail() + " | " + e.getPhoneNumber() +
                " | " + e.getHireDate() + " | " + e.getSalary() + " | " + e.getCommisionPct() + " | " + (e.getManager()!=null?e.getManager().getId():"-") +
                " | " + e.getJob().getId() + " | " + e.getDepartment().getName()) ;
        return e;
    }

    private Job createJob(String id, String title, double minSalary, double maxSalary) {
        Job j = this.jobRepository.save(new Job(id, title, minSalary, maxSalary));
        logger.info(j.getId() + " | " + j.getTitle() + " | " + j.getMinSalary() + " | " + j.getMaxSalary());
        return j;
    }

    private Department createDepartment(String name, Employee manager, Location location) {
        Department d = this.departmentRepository.save(new Department(name, manager, location));
        logger.info(d.getId() + " | " + (d.getManager()!=null?d.getManager().getId():"-") + " | " + location.getStreetAddress());
        return d;
    }

    private Location createLocation(String streetAddress, String postalCode, String city, String stateProvince, Country country) {
        Location l = this.locationRepository.save(new Location(streetAddress, postalCode, city, stateProvince, country));
        logger.info(l.getId() + " | " + l.getCity() + " | " + l.getPostalCode() + " | " + l.getStateProvince() + " | " + country.getName());
        return l;
    }

    private Country createCountry(String id, String name, Region region) {
        Country c = this.countryRepository.save(new Country(id, name, region));
        logger.info(c.getId() + " | " + c.getName() + " | " + region.getName());
        return c;
    }

    private Region createRegion(String name){
        Region r = this.regionRepository.save(new Region(name));
        logger.info(r.getId() + " | " + r.getName());
        return r;
    }

}