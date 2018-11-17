package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "JOB_HISTORY")
public class JobHistory {

    @EmbeddedId
    private JobHistoryID id;

    @ApiModelProperty(notes = "La fecha de fin", required = true)
    @NotNull
    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne
    @NotNull
    @JoinColumn(name="JOB_ID", nullable=false)
    private Job job;

    @ManyToOne
    @JoinColumn(name="DEPARTMENT_ID")
    private Department department;

    public JobHistory() {
    }

    public Employee getEmployee() {
        return this.id.getEmployee();
    }

    public void setEmployee(Employee employee) {
        this.id.setEmployee(employee);
    }

    public Date getStartDate() {
        return this.id.getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.id.setStartDate(startDate);
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