package com.alumni.userservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Address {

    @NotBlank
    @Size(min = 2, max = 100)
    private String country;

    @NotBlank
    @Size(min = 1, max = 100)
    private String city;

    @Size(max = 255)
    private String street;

    @Size(max = 20)
    private String postalCode;

    public Address() {
    }

    public Address(String country, String city, String street, String postalCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
