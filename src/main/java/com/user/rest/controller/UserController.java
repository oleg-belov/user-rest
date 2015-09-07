package com.user.rest.controller;

import com.user.rest.entity.Response;
import com.user.rest.entity.User;
import com.user.rest.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
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
    public User getUser(@RequestParam("id") long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id is less that 0");
        }
        return userRepository.getUser(id);

    }

    @RequestMapping(value = "users", method = RequestMethod.POST, produces = "application/json")
    public Response createUser(@RequestBody User user) {
        long insertedId = userRepository.insert(user);
        user.setUid(insertedId);
        Response response = new Response(HttpStatus.CREATED);
        response.setUser(user);
        return response;
    }

    @RequestMapping(value = "users/user1", method = RequestMethod.PUT)
    public Response updateUser(@RequestParam("id") long id, @RequestParam("name") String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name is empty or null");
        }
        User user = getUser(id);
        userRepository.update(user, name);
        user.setName(name);
        Response response = new Response(HttpStatus.ACCEPTED);
        response.setUser(user);
        return response;
    }

    @RequestMapping(value = "users/user1", method = RequestMethod.DELETE)
    public Response deleteUser(@RequestParam("id") long id) {
        User user = getUser(id);
        int deleted = userRepository.delete(user);
        return new Response(HttpStatus.OK);
    }
}
