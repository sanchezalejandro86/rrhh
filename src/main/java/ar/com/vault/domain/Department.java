package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "DEPARTMENTS")
public class Department {

    @ApiModelProperty(notes = "Id del departamento")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @ApiModelProperty(notes = "El nombre del departamento", required = true)
    @NotNull
    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name="MANAGER_ID")
    private Employee manager;

    @ManyToOne
    @JoinColumn(name="LOCATION_ID")
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