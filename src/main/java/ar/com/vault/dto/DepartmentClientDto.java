package ar.com.vault.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by alejandro on 17/11/18.
 */
public class DepartmentClientDto {
    @ApiModelProperty(notes = "El nombre del departamento")
    private String name;

    @ApiModelProperty(notes = "El ID del manager")
    private Long managerId;

    @ApiModelProperty(notes = "El ID de la ubicación")
    private Long locationId;

    public DepartmentClientDto(String name, Long managerId, Long locationId) {
        this.name = name;
        this.managerId = managerId;
        this.locationId = locationId;
    }

    public DepartmentClientDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
