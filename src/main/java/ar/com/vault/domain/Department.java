package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "DEPARTMENTS")
public class Department {

    @ApiModelProperty(notes = "Id del departamento")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "department")
    @JsonManagedReference
    List<Employee> employees;

    public Department(@NotNull String name, Employee manager, Location location) {
        this.name = name;
        this.manager = manager;
        this.location = location;
    }

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}