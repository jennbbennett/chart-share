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
    private String birthDate;
    private String insurance;
    private String insurancePolicy;
    private String allergy;
    private String condition;
    private String surgery;
    private String hospital;


    public Person() {
    }

    public Person(String firstName, String lastName, Address homeAddress, long groupId, String birthDate, String insurance, String insurancePolicy, String allergy, String condition, String surgery, String hospital) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.groupId = groupId;
        this.birthDate = birthDate;
        this.insurance = insurance;
        this.insurancePolicy = insurancePolicy;
        this.allergy = allergy;
        this.condition = condition;
        this.surgery = surgery;
        this.hospital = hospital;
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

    public String getBirthDate() {
        return birthDate;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getInsurancePolicy() {
        return insurancePolicy;
    }

    public String getAllergy() {
        return allergy;
    }

    public String getCondition() {
        return condition;
    }

    public String getSurgery() {
        return surgery;
    }

    public String getHospital() {
        return hospital;
    }
}
