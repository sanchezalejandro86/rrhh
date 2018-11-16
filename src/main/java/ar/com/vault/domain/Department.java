package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "DEPARTMENTS")
public class Department {

    @ApiModelProperty(notes = "Id del departamento")
    @Id
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @ApiModelProperty(notes = "El nombre del departamento", required = true)
    @NotNull
    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 30)
    private String name;

    @ManyToOne
    @Column(name = "MANAGER_ID")
    private Employee manager;

    @ManyToOne
    @Column(name = "LOCATION_ID")
    private Location location;

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}