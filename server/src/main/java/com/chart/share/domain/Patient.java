package com.chart.share.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jenn on 3/20/16.
 */
public class Patient {
    @Id
    ObjectId id;
    long physicianId;
    long personId;
    Date dateAdded;

    public Patient(long physicianId, long personId, Date dateAdded) {
        this.physicianId = physicianId;
        this.personId = personId;
        this.dateAdded = dateAdded;
    }

    public long getPhysicianId() {
        return physicianId;
    }

    public long getPersonId() {
        return personId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
}
