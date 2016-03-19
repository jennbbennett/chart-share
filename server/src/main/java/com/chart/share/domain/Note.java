package com.chart.share.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by jenn on 3/19/16.
 */
public class Note {
    @Id
    private long id;
    private String text;
    private DomainType targetType;
    private long targetId;

    public Note(String text, DomainType targetType, long targetId) {
        this.text = text;
        this.targetType = targetType;
        this.targetId = targetId;
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
}
