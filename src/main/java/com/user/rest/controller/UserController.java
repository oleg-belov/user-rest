package com.user.rest.controller;

import com.user.rest.entity.User;
import com.user.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "users", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        int number = userRepository.insert(user);
        return ResponseEntity.ok(number);
    }

    @RequestMapping(value = "users/user1")
    public User getUser(@RequestParam("id") long id) {
        return userRepository.getUser(id);
    }
}
