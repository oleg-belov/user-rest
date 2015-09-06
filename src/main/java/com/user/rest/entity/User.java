package com.user.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sergey on 05.09.15.
 */
public class User {

    private String name;
    private long uid;

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    @JsonProperty(required = false)
    public void setUid(long uid) {
        this.uid = uid;
    }
}
