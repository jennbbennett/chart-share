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
    private String email;
    private String phoneNumber;
    private String practice;
    private long groupId;
    private long personId;

    public Physician(){
    }

    public Physician(String firstName, String lastName, Address officeAddress, String email, String phoneNumber, String practice, long groupId, long personId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.officeAddress = officeAddress;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.practice = practice;
        this.groupId = groupId;
        this.personId = personId;
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

    public String getPractice() {
        return practice;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getPersonId() {
        return personId;
    }

    public long getGroupId() {
        return groupId;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
