package com.chart.share.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jenn on 3/15/16.
 */
public class Medication {

    @Id
    private long id;
    private String rxName;
    private long rxNumber;
    private long rxQuantity;
    private long rxRefillNumber;
    private Date rxDate;
    private long PhysicianId;
    private long groupId;
    private long personId;

    public Medication() {

    }

    public Medication(String rxName, long rxNumber, long rxQuantity, long rxRefillNumber, Date rxDate, long physicianId, long groupId, long personId) {
        this.rxName = rxName;
        this.rxNumber = rxNumber;
        this.rxQuantity = rxQuantity;
        this.rxRefillNumber = rxRefillNumber;
        this.rxDate = rxDate;
        PhysicianId = physicianId;
        this.groupId = groupId;
        this.personId = personId;
    }

    public long getId() {
        return id;
    }

    public String getRxName() {
        return rxName;
    }

    public long getRxNumber() {
        return rxNumber;
    }

    public long getRxQuantity() {
        return rxQuantity;
    }

    public long getRxRefillNumber() {
        return rxRefillNumber;
    }

    public Date getRxDate() {
        return rxDate;
    }

    public long getPhysicianId() {
        return PhysicianId;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setId(long id) {
        this.id = id;
    }
}
