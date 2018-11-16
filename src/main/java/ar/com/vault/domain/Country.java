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
@Entity(name = "COUNTRIES")
public class Country {

    @ApiModelProperty(notes = "Id del país")
    @Id
    @Column(name = "COUNTRY_ID")
    private String id;

    @ApiModelProperty(notes = "El nombre del país")
    @Column(name = "COUNTRY_NAME", length = 40)
    private String name;

    @ManyToOne
    @Column(name = "REGION_ID")
    private Region region;

    public Country() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}