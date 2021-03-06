package ar.com.vault.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by alejandro on 17/11/18.
 */
public class EmployeeDTO {

    @ApiModelProperty(notes = "El ID del empleado")
    private Long id;

    @ApiModelProperty(notes = "El nombre del empleado")
    private String firstname;

    @ApiModelProperty(notes = "El apellido del empleado", required = true)
    @NotNull
    private String lastname;

    @ApiModelProperty(notes = "El email del empleado", required = true)
    @NotNull
    private String email;

    @ApiModelProperty(notes = "El telefono del empleado")
    private String phoneNumber;

    @ApiModelProperty(notes = "La fecha de contratación empleado", required = true)
    @NotNull
    private Date hireDate;

    @ApiModelProperty(notes = "El email del empleado", required = true)
    private Double salary;

    @ApiModelProperty(notes = "El email del empleado", required = true)
    private Double commisionPct;

    public EmployeeDTO(Long id, String firstname, @NotNull String lastname, @NotNull String email, String phoneNumber, @NotNull Date hireDate, Double salary, Double commisionPct) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commisionPct = commisionPct;
    }

    public EmployeeDTO(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommisionPct() {
        return commisionPct;
    }

    public void setCommisionPct(Double commisionPct) {
        this.commisionPct = commisionPct;
    }
}
