package com.chart.share.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by jenn on 3/15/16.
 */
public class Address {
    @Id
    private long id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private long zipCode;

    public long getPhysicianId() {
        return PhysicianId;
    }

    private long PhysicianId;

    public Address(){

    }


    public void setId(long id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public long getZipCode() {
        return zipCode;
    }
}
