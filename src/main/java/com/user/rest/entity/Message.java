package com.user.rest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sergey on 06.09.15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
