package com.chart.share.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by jenn on 3/14/16.
 */
public class Physician {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private long AddressId;

    public Physician(){
    }

    public Physician(String firstName, String lastName, long AddressId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.AddressId = AddressId;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getAddress() {
        return AddressId;
    }

    public void setId(long id) {
        this.id = id;
    }

}
