package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "REGIONS")
public class Region {

    @ApiModelProperty(notes = "Id de la región")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REGION_ID")
    private Long id;

    @ApiModelProperty(notes = "El nombre de la región")
    @Column(name = "REGION_NAME", length = 25)
    private String name;

    public Region(String name) {
        this.name = name;
    }

    public Region() {
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

}