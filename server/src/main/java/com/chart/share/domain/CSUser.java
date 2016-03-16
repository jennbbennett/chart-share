package com.chart.share.domain;


import org.springframework.data.annotation.Id;
/**
 * Created by jenn on 3/13/16.
 */
public class    CSUser {

    @Id
    private long id;
    private String authId;
    private String authSource;
    private long personId;

    public CSUser(String authSource, String authId, long personId) {
        this.authSource = authSource;
        this.authId = authId;
        this.personId = personId;
    }

    public String getAuthSource() {
        return authSource;
    }

    public long getId() {
        return id;
    }

    public String getAuthId() {
        return authId;
    }

    public long getPersonId() {
        return personId;
    }
}
