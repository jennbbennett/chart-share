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

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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


    public void setId(long id) {
        this.id = id;
    }
}
