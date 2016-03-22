package com.chart.share.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by jenn on 3/13/16.
 */
public class Person {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private Address homeAddress;
    private long groupId;


    public Person() {
    }

    public Person(String firstName, String lastName, Address homeAddress, long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public long getGroupId() {
        return groupId;
    }
}
