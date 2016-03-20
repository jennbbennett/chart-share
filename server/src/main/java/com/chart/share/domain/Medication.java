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
    private String refills;
    private Date rxDate;
    private long physicianId;
    private long groupId;
    private long personId;
    private long rxCui;

    public Medication() {

    }

    public Medication(String rxName, long rxNumber, long rxQuantity, String refills, Date rxDate, long physicianId, long groupId, long personId, long rxCui) {
        this.rxName = rxName;
        this.rxNumber = rxNumber;
        this.rxQuantity = rxQuantity;
        this.refills = refills;
        this.rxDate = rxDate;
        this.physicianId = physicianId;
        this.groupId = groupId;
        this.personId = personId;
        this.rxCui = rxCui;

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

    public String getRefills() {
        return refills;
    }

    public Date getRxDate() {
        return rxDate;
    }

    public long getPhysicianId() {
        return physicianId;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getPersonId() {
        return personId;
    }

    public long getRxCui() {
        return rxCui;
    }

    public void setId(long id) {
        this.id = id;
    }
}
