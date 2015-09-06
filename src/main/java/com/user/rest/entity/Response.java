package com.user.rest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

/**
 * Created by sergey on 06.09.15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    HttpStatus status;
    User user;

    public Response(HttpStatus status) {
        this.status = status;
    }



    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
