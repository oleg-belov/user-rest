package com.user.rest.controller;

import com.user.rest.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sergey on 05.09.15.
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final AtomicLong counter = new AtomicLong();

    private static List<User> users = new ArrayList<User>();

    @RequestMapping(value = "users",method = RequestMethod.GET)
    public List<User> users() {
        return users;
    }

    @RequestMapping(value = "users", method = RequestMethod.POST,consumes ="application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setUid(counter.incrementAndGet());
        users.add(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
