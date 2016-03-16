package com.chart.share.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jenn on 3/14/16.
 */
public class GroupMember {
    @Id
    long id;
    long groupId;
    long memberId;
    Date dateAdded;

    public GroupMember(long id, long groupId, long memberId, Date dateAdded) {
        this.id = id;
        this.groupId = groupId;
        this.memberId = memberId;
        this.dateAdded = dateAdded;
    }

    public long getId() {
        return id;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getMemberId() {
        return memberId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }
}



