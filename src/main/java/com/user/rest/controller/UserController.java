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
@RequestMapping("/rest/")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> users() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "users/user")
    public User getUser(@RequestParam("id") String id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "users/update", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "users/user/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam("id") String id) {
        userRepository.delete(id);
    }
}
