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

    @RequestMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "user/update/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable String id,@RequestBody User user) {
        User dbUser = userRepository.findOne(id);
        dbUser.setEmail(user.getEmail());
        dbUser.setFirstname(user.getFirstname());
        dbUser.setLastname(user.getLastname());
        dbUser.setUsername(user.getUsername());
        return userRepository.save(dbUser);
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") String id) {
        userRepository.delete(id);
    }
}
