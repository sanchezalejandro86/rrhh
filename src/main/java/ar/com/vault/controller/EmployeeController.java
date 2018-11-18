package ar.com.vault.controller;

import ar.com.vault.domain.Employee;
import ar.com.vault.dto.EmployeeClientDto;
import ar.com.vault.dto.EmployeeDTO;
import ar.com.vault.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by alejandro on 17/11/18.
 */
@RestController
@RequestMapping(path = "/employees")
@Api(value="Employee",description = "Servicio de empleados")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Obtener un Empleado", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El Empleado fue retornado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{employeeId}", method = RequestMethod.GET)
    public EmployeeDTO getById(@PathVariable Long employeeId) {
        return this.employeeService.getById(employeeId);
    }

    @ApiOperation(value = "Crear nuevo Employee", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El Empleado fue creado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Employee create(@Valid @RequestBody EmployeeClientDto employeeDto) {
        return this.employeeService.save(employeeDto);
    }

    @ApiOperation(value = "Crear nuevo Employee", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El Empleado fue creado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{employeeId}", method = RequestMethod.POST)
    public Employee update(@PathVariable Long employeeId, @Valid @RequestBody EmployeeClientDto employeeDto) {
        return this.employeeService.update(employeeId, employeeDto);
    }

    @ApiOperation(value = "Eliminar Empleado", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El Empleado fue eliminado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{employeeId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long employeeId) {
        this.employeeService.delete(employeeId);
    }

    @ApiOperation(value = "Obtener los empleados", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El listado de empleados fue retornado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/", params = { "job_id", "manager_id", "lastname", "page", "size" }, method = RequestMethod.GET)
    public Page<Employee> search(@RequestParam("job_id") String jobId,
                                 @RequestParam("manager_id") Long managerId,
                                 @RequestParam("lastname") String lastname,
                                 @RequestParam("page") int page,
                                 @RequestParam("size") int size
                                 ){
        return this.employeeService.search(jobId, managerId, lastname, page, size);
    }
}
