package ar.com.vault.controller;

import ar.com.vault.domain.Department;
import ar.com.vault.domain.Employee;
import ar.com.vault.dto.DepartmentClientDto;
import ar.com.vault.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by alejandro on 17/11/18.
 */
@RestController
@RequestMapping(path = "/departments")
@Api(value="Department",description = "Servicio de departamentos")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "El Empleado fue creado exitosamente"),
            @ApiResponse(code = 401, message = "No está autorizado a ver el recurso"),
            @ApiResponse(code = 403, message = "Tiene prohibido el acceso al recurso"),
            @ApiResponse(code = 404, message = "No se puede encontrar el recurso"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Department create(@Valid @RequestBody DepartmentClientDto departmentDto) {
        return this.departmentService.save(departmentDto);
    }

}
