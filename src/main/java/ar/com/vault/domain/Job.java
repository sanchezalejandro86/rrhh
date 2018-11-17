package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "JOBS")
public class Job {

    @ApiModelProperty(notes = "Id del trabajo")
    @Id
    @Column(name = "JOB_ID")
    private String id;

    @ApiModelProperty(notes = "El título del trabajo")
    @NotNull
    @Column(name = "JOB_TITLE", nullable = false, length = 35)
    private String title;

    @ApiModelProperty(notes = "El salario mínimo")
    @Column(name = "MIN_SALARY")
    private Double minSalary;

    @ApiModelProperty(notes = "El salario máximo")
    @Column(name = "MAX_SALARY")
    private Double maxSalary;

    public Job() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }
}