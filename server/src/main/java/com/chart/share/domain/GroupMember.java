package com.chart.share.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jenn on 3/14/16.
 */
public class GroupMember {
    @Id
    ObjectId id;
    long groupId;
    long personId;
    Date dateAdded;

    public GroupMember(long groupId, long personId, Date dateAdded) {
        this.groupId = groupId;
        this.personId = personId;
        this.dateAdded = dateAdded;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getPersonId() {
        return personId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
}



