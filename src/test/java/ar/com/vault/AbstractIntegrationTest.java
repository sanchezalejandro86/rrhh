package ar.com.vault;

import ar.com.vault.domain.*;
import ar.com.vault.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = {
                RRHHApplication.class},
        webEnvironment = WebEnvironment.DEFINED_PORT
)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractIntegrationTest {

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    protected MockMvc mockMvc;

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

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                mappingJackson2HttpMessageConverter);
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


    @Before
    public void setup() throws Exception {
        //Insert initial values
        Region r1 = this.regionRepository.save(new Region("Sudamérica"));
        Country c1 = this.countryRepository.save(new Country("AR", "Argentina", r1));
        Location l1 = this.locationRepository.save(new Location("Av. Alberdi 140", "1424", "CABA", "CABA", c1));
        Department d1 = this.departmentRepository.save(new Department("Desarrollo", null, l1));
        Job j1 = this.jobRepository.save(new Job("TECH-LEAD", "Líder Técnico", 100d, 1000d));
        Employee e1 = this.employeeRepository.save(new Employee("Jorge", "Mansaro", "jorge@gmail.com", "1564646464", new Date(118, 11, 1), 100d, 1d, null, j1, d1));

        Job j2 = this.jobRepository.save(new Job("DEV-SR", "Desarrollador SR", 200d, 700d));
        Employee e2 = this.employeeRepository.save(new Employee("Juan", "Perez", "jperez@gmail.com", "1533333333", new Date(115, 11, 1), 200d, 1d, e1, j2, d1));
    }

    @After
    public void clean() {
        this.departmentRepository.deleteAll();
        this.locationRepository.deleteAll();
        this.countryRepository.deleteAll();
        this.regionRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.jobRepository.deleteAll();
    }

}