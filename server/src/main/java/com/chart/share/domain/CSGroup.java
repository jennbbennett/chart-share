package com.chart.share.domain;

import org.springframework.data.annotation.Id;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenn on 3/14/16.
 */
public class CSGroup {
    @Id
    long id;
    String name;

    public CSGroup() {
    }

    public CSGroup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
