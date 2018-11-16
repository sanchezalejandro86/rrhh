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
@Entity(name = "LOCATIONS")
public class Location {

    @ApiModelProperty(notes = "Id de la ubicación")
    @Id
    @Column(name = "LOCATION_ID")
    private Long id;

    @ApiModelProperty(notes = "La dirección de la ubicación")
    @Column(name = "STREET_ADDRESS", length = 40)
    private String streetAddress;

    @ApiModelProperty(notes = "La ciudad de la ubicación", required = true)
    @NotNull
    @Column(name = "CITY", length = 30)
    private String city;

    @ApiModelProperty(notes = "La provincia de la ubicación")
    @Column(name = "STATE_PROVINCE", length = 25)
    private String stateProvince;

    @ManyToOne
    @Column(name = "COUNTRY_ID")
    private Country country;

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}