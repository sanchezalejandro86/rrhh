package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "EMPLOYEES")
public class Employee {

    @ApiModelProperty(notes = "Id del empleado")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @ApiModelProperty(notes = "El nombre del empleado")
    @Column(name = "FIRST_NAME", nullable = false, length=20)
    private String firstname;

    @ApiModelProperty(notes = "El apellido del empleado", required = true)
    @NotNull
    @Column(name = "LAST_NAME", length=25)
    private String lastname;

    @ApiModelProperty(notes = "El email del empleado", required = true)
    @NotNull
    @Column(name = "EMAIL", length=25)
    private String email;

    @ApiModelProperty(notes = "El telefono del empleado")
    @Column(name = "PHONE_NUMBER", nullable = false, length=20)
    private String phoneNumber;

    @ApiModelProperty(notes = "La fecha de contratación empleado", required = true)
    @NotNull
    @Column(name = "HIRE_DATE")
    private Date hireDate;

    @ApiModelProperty(notes = "El email del empleado", required = true)
    @Column(name = "SALARY", nullable = false)
    private Double salary;

    @ApiModelProperty(notes = "El email del empleado", required = true)
    @Column(name = "COMMISSION_PCT", nullable = false)
    private Double commisionPct;

    @ManyToOne
    @JoinColumn(name="MANAGER_ID")
    private Employee manager;

    @ManyToOne
    @NotNull
    @JoinColumn(name="JOB_ID", nullable=false)
    private Job job;

    @ManyToOne
    @JoinColumn(name="DEPARTMENT_ID")
    private Department department;

    public Employee(){
    }

    public Long getId() {
        return id;
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

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}