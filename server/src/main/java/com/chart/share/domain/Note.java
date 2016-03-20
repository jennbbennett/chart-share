package com.chart.share.domain;

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
    private Date dateAdded;

    public Note(String text, DomainType targetType, long targetId, Date dateAdded) {
        this.text = text;
        this.targetType = targetType;
        this.targetId = targetId;
        this.dateAdded = dateAdded;
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

    public void set(long id) {
    }
}
