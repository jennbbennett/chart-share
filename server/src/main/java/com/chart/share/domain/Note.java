package com.chart.share.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jenn on 3/19/16.
 */
public class Note {
    @Id
    private long id;
    private String text;
    private DomainType targetType;
    private long targetId;
    @JsonFormat(pattern = "MM-dd-yyyy hh:mm aa")
    private Date dateAdded;
    private String title;
    private long groupId;

    public Note() {
    }

    public Note(String text, DomainType targetType, long targetId, Date dateAdded, String title, long groupId) {
        this.text = text;
        this.targetType = targetType;
        this.targetId = targetId;
        this.dateAdded = dateAdded;
        this.title = title;
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public DomainType getTargetType() {
        return targetType;
    }

    public long getTargetId() {
        return targetId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getTitle() {
        return title;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getGroupId() {
        return groupId;
    }
}
