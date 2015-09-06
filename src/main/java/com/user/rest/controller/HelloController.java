package com.user.rest.controller;

import com.user.rest.entity.Message;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sergey on 05.09.15.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
    public Message index() {
        return new Message("Greetings from User Rest!");
    }
}
