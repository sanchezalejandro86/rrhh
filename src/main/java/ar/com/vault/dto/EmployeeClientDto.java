package ar.com.vault.dto;

import ar.com.vault.domain.Department;
import ar.com.vault.domain.Employee;
import ar.com.vault.domain.Job;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by alejandro on 17/11/18.
 */
public class EmployeeClientDto {
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

    @ApiModelProperty(notes = "El Id del Job", required = true)
    private String jobId;

    @ApiModelProperty(notes = "El Id del Manager")
    private Long managerId;

    @ApiModelProperty(notes = "El Id del Departamento")
    private Long departmentId;

    public EmployeeClientDto(String firstname, @NotNull String lastname, @NotNull String email, String phoneNumber, @NotNull Date hireDate, Double salary, Double commisionPct, String jobId, Long managerId, Long departmentId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commisionPct = commisionPct;
        this.jobId = jobId;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

    public EmployeeClientDto(){
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

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
