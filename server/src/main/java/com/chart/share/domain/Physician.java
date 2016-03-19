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
    private Address officeAddress;
    private long groupId;

    public Physician(){
    }

    public Physician(String firstName, String lastName, Address officeAddress, long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeAddress = officeAddress;
        this.groupId = groupId;
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

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public long getGroupId() {
        return groupId;
    }
    public void setId(long id) {
        this.id = id;
    }
}
