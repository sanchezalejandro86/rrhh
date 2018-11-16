package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "REGIONS")
public class JobHistory {

    @ManyToOne
    @NotNull
    @Column(name = "EMPLOYEE_ID")
    private Employee employee;

    @ApiModelProperty(notes = "La fecha de inicio", required = true)
    @NotNull
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @ApiModelProperty(notes = "La fecha de fin", required = true)
    @NotNull
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @ManyToOne
    @NotNull
    @Column(name = "JOB_ID")
    private Job job;

    @ManyToOne
    @Column(name = "DEPARTMENT_ID")
    private Department department;

    public JobHistory() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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