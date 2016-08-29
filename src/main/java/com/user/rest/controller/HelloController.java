package com.user.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sergey on 05.09.15.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {
        return "Greetings from User Rest!";
    }
}
