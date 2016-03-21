package com.chart.share.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jenn on 3/21/16.
 */
public class Activity {
    @Id
    private ObjectId id;
    private DomainType targetType;
    private long targetId;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm aa")
    private Date activityDate;
    private String description;
    private long groupId;

    public Activity(DomainType targetType, long targetId, Date activityDate, String description, long groupId) {
        this.targetType = targetType;
        this.targetId = targetId;
        this.activityDate = activityDate;
        this.description = description;
        this.groupId = groupId;
    }

    public DomainType getTargetType() {
        return targetType;
    }

    public long getTargetId() {
        return targetId;
    }

    public long getGroupId() {
        return groupId;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public String getDescription() {
        return description;
    }
}
