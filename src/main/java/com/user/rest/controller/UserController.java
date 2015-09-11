package com.user.rest.controller;

import com.user.rest.entity.Response;
import com.user.rest.entity.User;
import com.user.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sergey on 05.09.15.
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> users() {
        return userRepository.getUsers();
    }

    @RequestMapping(value = "users/user1")
    public Response getUser(@RequestParam("id") long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is less that 0");
        }
        User user = userRepository.getUser(id);
        Response response = new Response(HttpStatus.OK);
        response.setUser(user);
        return response;

    }

    @RequestMapping(value = "users", method = RequestMethod.POST)
    public Response createUser(@RequestBody User user) {
        long insertedId = userRepository.insert(user);
        user.setUid(insertedId);
        Response response = new Response(HttpStatus.CREATED);
        response.setUser(user);
        return response;
    }

    @RequestMapping(value = "users/user1", method = RequestMethod.PUT)
    public Response updateUser(@RequestParam("id") long id, @RequestBody User user) {
        userRepository.update(id, user);
        Response response = new Response(HttpStatus.ACCEPTED);
        response.setUser(user);
        return response;
    }

    @RequestMapping(value = "users/user1", method = RequestMethod.DELETE)
    public Response deleteUser(@RequestParam("id") long id) {
        userRepository.delete(id);
        return new Response(HttpStatus.OK);
    }
}
