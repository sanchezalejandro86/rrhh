package ar.com.vault.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by alejandro on 16/11/18.
 */
@Entity(name = "LOCATIONS")
public class Location {

    @ApiModelProperty(notes = "Id de la ubicación")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_ID")
    private Long id;

    @ApiModelProperty(notes = "La dirección de la ubicación")
    @Column(name = "STREET_ADDRESS", length = 40)
    private String streetAddress;


    @ApiModelProperty(notes = "El código postal de la ubicación")
    @Column(name = "POSTAL_CODE", length = 12)
    private String postalCode;

    @ApiModelProperty(notes = "La ciudad de la ubicación", required = true)
    @NotNull
    @Column(name = "CITY", length = 30)
    private String city;

    @ApiModelProperty(notes = "La provincia de la ubicación")
    @Column(name = "STATE_PROVINCE", length = 25)
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    private Country country;

    public Location(String streetAddress, @NotNull String city, String postalCode, String stateProvince, Country country) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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