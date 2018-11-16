package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "REGIONS")
public class Region {

    @ApiModelProperty(notes = "Id de la región")
    @Id
    @Column(name = "REGION_ID")
    private String id;

    @ApiModelProperty(notes = "El nombre de la región")
    @Column(name = "REGION_NAME", length = 25)
    private String name;

    public Region() {
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

}