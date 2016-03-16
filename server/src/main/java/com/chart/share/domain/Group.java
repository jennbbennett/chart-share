package com.chart.share.domain;

import org.springframework.data.annotation.Id;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jenn on 3/14/16.
 */
public class Group {
    @Id
    long id;
    String name;

    List<GroupMember> members;



    public Group(long id, String name) {
        this.id = id;
        this.name = name;
        members = new LinkedList<GroupMember>();
    }

    public Group(long id, String name, List<GroupMember> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setId(long id) {
        this.id = id;
    }
}
