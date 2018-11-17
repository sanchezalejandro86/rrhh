package ar.com.vault.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by alejandro on 16/11/18.
 */
@Embeddable
public class JobHistoryID implements Serializable {

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    public JobHistoryID(Employee employee, Date startDate) {
        this.employee = employee;
        this.startDate = startDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobHistoryID)) return false;
        JobHistoryID that = (JobHistoryID) o;
        return Objects.equals(getEmployee().getId(), that.getEmployee().getId()) &&
                Objects.equals(getStartDate(), that.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployee().getId(), getStartDate());
    }
}